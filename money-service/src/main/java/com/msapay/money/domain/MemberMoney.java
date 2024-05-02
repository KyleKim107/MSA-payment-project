package com.msapay.money.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
@Getter
public class MemberMoney {
    private final String memberMoneyId;
    private final String membershipId;
    private final int balance;

    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId
            , MembershipId membershipId
            , MoneyBalance balance
    ) {
        return new MemberMoney(
                memberMoneyId.memberMoneyId
                , membershipId.membershipId
                , balance.balance
        );
    }
    @Value
    public static class MemberMoneyId
    {
        String memberMoneyId;
        public MemberMoneyId(String memberMoneyId){
            this.memberMoneyId = memberMoneyId;
        }
    }

    @Value
    public static class MembershipId
    {
        String membershipId;
        public MembershipId(String membershipId){
            this.membershipId = membershipId;
        }
    }

    @Value
    public static class MoneyBalance {
        int balance;
        public MoneyBalance(int balance){
            this.balance = balance;
        }
    }

}
