package com.msapay.money.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
public class MoneyChangingRequest {
    private final String moneyChangingRequestId;    // field에 final을 붙이면 생성자에 의해 생성될때 주입받은 값 외로 변경 불가능하다.

    // 어떤 고객의 증액/감액 요청인지 알기 위해 필요한 값
    private final String targetMembershipId;

    // 어떤 은행의 계좌로 증액/감액 요청인지 알기 위해 필요한 값
    private final int changingType; // ENUM

    // 요청 금액
    private final int changingMoneyAmount;

    // 요청에 대한 상태
    private final int changingMoneyStatus;// 0:요청 1:성공 2:실패


    private final String uuid;
    private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId
            ,TargetMembershipId targetMembershipId
            , MoneyChangingType changingType
            , ChangingMoneyAmount changingMoneyAmount
            , MoneyChangingStatus changingMoneyStatus
            , Uuid uuid
    ) {
       return new MoneyChangingRequest(
               moneyChangingRequestId.moneyChangingRequestId
               ,targetMembershipId.targetMembershipId
               ,changingType.changingType
               ,changingMoneyAmount.changingMoneyAmount
               ,changingMoneyStatus.changingMoneyStatus
               ,uuid.getUuid()
               ,new Date()
       );
    }
    @Value
    public static class MoneyChangingRequestId{
        String moneyChangingRequestId;
        public MoneyChangingRequestId(String moneyChangingRequestId){
            this.moneyChangingRequestId = moneyChangingRequestId;
        }
    }

    @Value
    public static class TargetMembershipId{
        String targetMembershipId;
        public TargetMembershipId(String targetMembershipId){
            this.targetMembershipId = targetMembershipId;
        }
    }
    @Value
    public static class MoneyChangingType{
        int changingType;
        public MoneyChangingType(int changingType){
            this.changingType = changingType;
        }
    }
    @Value
    public static class ChangingMoneyAmount{
        int changingMoneyAmount;
        public ChangingMoneyAmount(int changingMoneyAmount){
            this.changingMoneyAmount = changingMoneyAmount;
        }
    }
    @Value
    public static class MoneyChangingStatus {
        int changingMoneyStatus;
        public MoneyChangingStatus(int changingMoneyStatus){
            this.changingMoneyStatus = changingMoneyStatus;
        }
    }
    @Value
    public static class Uuid{
        String uuid;
        public Uuid(String uuid){
            this.uuid = uuid.toString();
        }
    }
}
