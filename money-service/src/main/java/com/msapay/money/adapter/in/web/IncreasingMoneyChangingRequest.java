package com.msapay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreasingMoneyChangingRequest {
    private String targetMembershipId;
    // 조건을 증액요청으로 한정한다면 changeType을 사용하지 않아도 된다.
    private int amount;

}
