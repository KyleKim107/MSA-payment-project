package com.msapay.money.application.port.service;

import com.msapay.common.CountDownLatchManager;
import com.msapay.common.RechargingMoneyTask;
import com.msapay.common.SubTask;
import com.msapay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.msapay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.msapay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.msapay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.msapay.money.application.port.out.GetMembershipPort;
import com.msapay.money.application.port.out.IncreaseMoneyPort;
import com.msapay.money.application.port.out.SendRechargingMoneyTaskPort;
import com.msapay.money.domain.MemberMoney;
import com.msapay.money.domain.MoneyChangingRequest;
import com.msapay.common.UseCase;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;
    private final GetMembershipPort getMembershipPort;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final CountDownLatchManager countDownLatchManager;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        //머니 충전, 증액이라는 과정
        // 1 고객 정보가 정상인지 확인 (membership)
        getMembershipPort.getMembership(command.getTargetMembershipId());
        // 2 고객의 연동된 계좌가 있는지 그리고 정상적인지 확인 (banking)

        // 3. 법인계좌 상태도 정상인지 확인 (banking)

        // 4. 증액을 위한 기록 MoneyChangingRequest (moneyChaningRequest)

        // 5. 펌뱅킹 수행(고객의 연동된 계좌 -> 패캠페이 법인 계좌) (banking)

        // 6-1 결과가 정상이라면 성공이라고 MoneyChangingRequest 상태값 변경후 리턴
        // 성공시 membership의 money를 증액
        MemberMoneyJpaEntity entity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MemberMoneyId(command.getTargetMembershipId())
                , command.getAmount()
        );
        if (entity != null) {
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            new MoneyChangingRequest.MoneyChangingType(1),
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            new MoneyChangingRequest.MoneyChangingStatus(1),
                            new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                    )
            );
        }

        // 6-2 결과가 비정상이라면 실패라고 MoneyChangingRequest 상태값 변경후 리턴
        return null;
    }

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {
        //subtask란 각 서비스에 특정 membershipId로 validation을 수행하는 서비스
        // 1. subtask, task
        SubTask validateMembershipIdTask = SubTask.builder()
                .subTaskName("validateMembershipId")
                .membershipID(command.getTargetMembershipId())
                .taskType("membership")
                .status("ready")
                .build();

        SubTask validateBankingTask = SubTask.builder()
                .subTaskName("validateBanking")
                .membershipID(command.getTargetMembershipId())
                .taskType("banking")
                .status("ready")
                .build();
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validateMembershipIdTask);
        subTaskList.add(validateBankingTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .taskName("rechargingMoneyTask")
                .subTaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipID(command.getTargetMembershipId())
                .toBankName("fastcampus") // 법인 계좌 은행 이름
                .build();

        // 2. kafka cluster produce 메세지큐 로깅 시작점.
        // Producer가 서브테스크 리스트를 보내면, 컨슈머가 서브테스크 리스트를 받아서 처리하고, 처리한 결과를 다시 프로듀서에게 보내는 방식
        sendRechargingMoneyTaskPort.sendRechargingMoneyTask(task);
        // 3. wait
        try { // 나중에 컨슈머가 rechargingMoneyTask를 받아서 처리할때까지 기다린다. countDown을 하게 됨
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();

        //3-1 task-consumer
        // 등록된 sub-task가 있는지 확인
        // 4. Task result consumer
            String result = countDownLatchManager.getDataForKey(task.getTaskID());
            if(result.equals("success")) {
                // 5. Consume ok, logic
                MemberMoneyJpaEntity entity = increaseMoneyPort.increaseMoney(
                        new MemberMoney.MemberMoneyId(command.getTargetMembershipId())
                        , command.getAmount()
                );
                if (entity != null) {
                    return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                                    new MoneyChangingRequest.MoneyChangingType(1),
                                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                                    new MoneyChangingRequest.MoneyChangingStatus(1),
                                    new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                            )
                    );
                }
            }
            else if (result.equals("failed")) {
                // 5. Consume ok, logic
            }
        // 5. Consume ok, logic


        }
        return null;
    }
}
