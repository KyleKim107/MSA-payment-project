package com.msapay.banking.adapter.out.persistence;

import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.common.PersistenceAdapter;
import com.msapay.banking.application.port.out.RequestFirmbankingPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort {
    private final RequestFirmbankingRepository firmbankingRequestRepository;

    @Override
    public RequestFirmbankingJpaEntity createRequestFirmbanking(
            FirmbankingRequest.FromBankName fromBankName
            , FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber
            , FirmbankingRequest.ToBankName toBankName
            , FirmbankingRequest.ToBankAccountNumber toBankAccountNumber
            , FirmbankingRequest.MoneyAmount moneyAmount
            , FirmbankingRequest.FirmbankingStatus firmbankingStatus) {

        RequestFirmbankingJpaEntity firmbankingRequest = firmbankingRequestRepository.save(
                new RequestFirmbankingJpaEntity(
                        fromBankName.getFromBankName()
                        ,fromBankAccountNumber.getFromBankAccountNumber()
                        ,toBankName.getToBankName()
                        ,toBankAccountNumber.getToBankAccountNumber()
                        ,moneyAmount.getMoneyAmount()
                        ,firmbankingStatus.getFirmbankingStatus()
                        , UUID.randomUUID()
                )
        );
        return firmbankingRequest;
    }

    @Override
    public RequestFirmbankingJpaEntity modifyRequestFirmbanking(RequestFirmbankingJpaEntity entity) {
        return firmbankingRequestRepository.save(entity);
    }
}
