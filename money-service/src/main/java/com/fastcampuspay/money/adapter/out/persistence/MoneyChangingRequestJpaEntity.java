package com.fastcampuspay.money.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="money_changing_request")
@Data
@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {
    @Id
    @GeneratedValue
    private  Long moneyChangingRequestId;
    private  String targetMembershipId;
    private  int moneyChangingType; // ENUM
    private  int moneyChangingAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;
    private int changingMoneyStatus; // 0:요청 1:성공 2:실패
    private String uuid;
    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyChangingAmount, Timestamp timestamp, int changingMoneyStatus, String uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyChangingAmount = moneyChangingAmount;
        this.timestamp = new Date(timestamp.getTime());
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid;
    }
}
