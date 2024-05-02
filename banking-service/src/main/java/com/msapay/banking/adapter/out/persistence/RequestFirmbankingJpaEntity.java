package com.msapay.banking.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="request_firmbanking")
@Data
@NoArgsConstructor
public class RequestFirmbankingJpaEntity {
    @Id
    @GeneratedValue
    private  Long requestFirmbankingId;
    private  String fromBankName;
    private  String fromBankAccountNumber;
    private  String toBankName;
    private  String toBankAccountNumber;
    private int moneyAmount;
    private int firmbankingStatus;// 0: 요청, 1: 완료, 2: 실패
    private String uuid;

    public RequestFirmbankingJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount, int firmbankingStatus, UUID uuid) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingStatus = firmbankingStatus;
        this.uuid = uuid.toString();
    }
}
