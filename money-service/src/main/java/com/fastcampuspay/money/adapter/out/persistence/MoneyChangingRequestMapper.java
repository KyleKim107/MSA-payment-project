package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity entity){

        return MoneyChangingRequest.generateMoneyChangingRequest(
                new MoneyChangingRequest.MoneyChangingRequestId(entity.getMoneyChangingRequestId()+""),
                new MoneyChangingRequest.TargetMembershipId(entity.getTargetMembershipId()),
                new MoneyChangingRequest.MoneyChangingType(entity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(entity.getMoneyChangingAmount()),
                new MoneyChangingRequest.MoneyChangingStatus(entity.getChangingMoneyStatus()),
                new MoneyChangingRequest.Uuid(entity.getUuid())
        );
    }
}
