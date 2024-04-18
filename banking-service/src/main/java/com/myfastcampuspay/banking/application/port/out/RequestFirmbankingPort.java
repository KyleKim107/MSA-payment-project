package com.myfastcampuspay.banking.application.port.out;


import com.myfastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.myfastcampuspay.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.myfastcampuspay.banking.domain.FirmbankingRequest;
import com.myfastcampuspay.banking.domain.RegisteredBankAccount;

public interface RequestFirmbankingPort {
    RequestFirmbankingJpaEntity createRequestFirmbanking(
//            FirmbankingRequest.FirmbankingRequestId firmbankingRequestId,
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus
    );

    RequestFirmbankingJpaEntity modifyRequestFirmbanking(
        RequestFirmbankingJpaEntity entity
    );

}
