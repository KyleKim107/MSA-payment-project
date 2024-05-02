package com.msapay.money.adapter.in.web;

import lombok.Getter;

@Getter
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;
    private int moneyChangingType; // enum 0: 증액 1: 감액
    private int moneyChangingResultStatus;
    private int amount;

    public MoneyChangingResultDetail(String moneyChangingRequestId, int moneyChangingType, int moneyChangingResultStatus, int amount) {
        this.moneyChangingRequestId = moneyChangingRequestId;
        this.moneyChangingType = moneyChangingType;
        this.moneyChangingResultStatus = moneyChangingResultStatus;
        this.amount = amount;
    }
}

