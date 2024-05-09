package com.msapay.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.msapay.money.application.port.out"})
public class MoneyApplication {
    public static void main(String[] args){
        SpringApplication.run(MoneyApplication.class,args);
    }
}
