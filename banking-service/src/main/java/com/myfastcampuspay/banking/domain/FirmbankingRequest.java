package com.myfastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    @Getter
    private final String firmbankingRequestId;
    @Getter
    private final String fromBankName;
    @Getter
    private final String fromBankAccountNumber;
    @Getter
    private final String toBankName;
    @Getter
    private final String toBankAccountNumber;
    @Getter
    private final int moneyAmount;
    @Getter
    private int firmbankingStatus;// 0: 요청, 1: 완료, 2: 실패
    @Getter
    private final UUID uuid;

    public static FirmbankingRequest generateFirmbankingRequest(
        FirmbankingRequestId firmbankingRequestId,
        FromBankName fromBankName,
        FromBankAccountNumber fromBankAccountNumber,
        ToBankName toBankName,
        ToBankAccountNumber toBankAccountNumber,
        MoneyAmount moneyAmount,
        FirmbankingStatus firmbankingStatus,
        UUID uuid
    ){
        return new FirmbankingRequest(
            firmbankingRequestId.getFirmbankingRequestId(),
            fromBankName.getFromBankName(),
            fromBankAccountNumber.getFromBankAccountNumber(),
            toBankName.getToBankName(),
            toBankAccountNumber.getToBankAccountNumber(),
            moneyAmount.getMoneyAmount(),
                firmbankingStatus.getFirmbankingStatus(),
            uuid
        );
    }
    @Value
    public static class FirmbankingRequestId{
        String firmbankingRequestId;
        public FirmbankingRequestId(String firmbankingRequestId){
            this.firmbankingRequestId = firmbankingRequestId;
        }
    }
    @Value
    public static class FromBankName{
        String fromBankName;
        public FromBankName(String fromBankName){
            this.fromBankName = fromBankName;
        }
    }
    @Value
    public static class FromBankAccountNumber{
        String fromBankAccountNumber;
        public FromBankAccountNumber(String fromBankAccountNumber){
            this.fromBankAccountNumber = fromBankAccountNumber;
        }
    }
    @Value
    public static class ToBankName{
        String toBankName;
        public ToBankName(String toBankName){
            this.toBankName = toBankName;
        }
    }
    @Value
    public static class ToBankAccountNumber{
        String toBankAccountNumber;
        public ToBankAccountNumber(String toBankAccountNumber){
            this.toBankAccountNumber = toBankAccountNumber;
        }
    }
    @Value
    public static class MoneyAmount{
        int moneyAmount;
        public MoneyAmount(int moneyAmount){
            this.moneyAmount = moneyAmount;
        }
    }
    @Value
    public static class FirmbankingStatus{
        int firmbankingStatus;
        public FirmbankingStatus(int firmbankingStatus){
            this.firmbankingStatus = firmbankingStatus;
        }
    }

}