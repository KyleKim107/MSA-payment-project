package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import com.msapay.common.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {
    private final MoneyChangingRequestRepository registeredBankAccountRepository;
    private final MemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingRequest.MoneyChangingType moneyChangingType, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus, MoneyChangingRequest.Uuid uuid) {
        return registeredBankAccountRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        moneyChangingType.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Timestamp(System.currentTimeMillis()),
                        moneyChangingStatus.getChangingMoneyStatus(),
                        uuid.getUuid()));
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MemberMoneyId memberMoneyId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity;
        try{
            Optional<MemberMoneyJpaEntity> optional = memberMoneyRepository.findByMembershipId(Long.parseLong(memberMoneyId.getMemberMoneyId()));
            entity =  optional.get();
            entity.setBalance(entity.getBalance() + increaseMoneyAmount);

            return memberMoneyRepository.save(entity);
        }catch (Exception e){
            entity = new MemberMoneyJpaEntity(
                    Long.parseLong(memberMoneyId.getMemberMoneyId()),
                    increaseMoneyAmount
            );
            memberMoneyRepository.save(entity);
        }

        return memberMoneyRepository.save(entity);
    }
}
