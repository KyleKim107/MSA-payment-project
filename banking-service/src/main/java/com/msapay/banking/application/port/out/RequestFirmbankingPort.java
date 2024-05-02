package com.msapay.banking.application.port.out;


import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;

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
