package com.msapay.money.application.port.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.common.CountDownLatchManager;
import com.msapay.common.LoggingProducer;
import com.msapay.common.RechargingMoneyTask;
import com.msapay.common.SubTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class RechargingMoneyResultConsumer {
    private final KafkaConsumer<String, String> consumer;

    private final LoggingProducer loggingProducer;
    @NotNull
    private final CountDownLatchManager countDownLatchManager;
    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                         @Value("${task.result.topic}")String topic, LoggingProducer loggingProducer, CountDownLatchManager countDownLatchManager) {
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.key()  + " / "+  record.value());
                        // record: RechargingMoneyTask, ( all subtask is done)
                        try{
                         Thread.sleep(3000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        RechargingMoneyTask task;
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        List<SubTask> subTaskList = task.getSubTaskList();

                        boolean taskResult = true;
                        // validation membership
                        // validation banking
                        for (SubTask subTask : subTaskList) {
                            // 모든 subtask 이 성공해야 task 는 성공.
                            // 한번만 실패해도 실패한 task 로 간주.
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }

                        // 모두 정상적으로 성공했다면
                        if (taskResult) {
                            // 성공하든 실패하든 한번 더 큐잉
                            this.loggingProducer.sendMessage(task.getTaskID(), "task success");
                            // 성공 한것에 대해서 키-밸류 저장
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");
                        } else{
                            this.loggingProducer.sendMessage(task.getTaskID(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "failed");
                        }
                        try{ // 테스
                            Thread.sleep(3000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        // 여기서 countDown 해야지 increaseMoneyRequestService의 락을 풀어준다.
                        this.countDownLatchManager.getCountDownLatch(task.getTaskID()).countDown();
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
