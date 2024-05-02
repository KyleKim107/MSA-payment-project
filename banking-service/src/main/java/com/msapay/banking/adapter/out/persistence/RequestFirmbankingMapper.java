package com.msapay.banking.adapter.out.persistence;

import com.msapay.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFirmbankingMapper {

    public FirmbankingRequest mapToDomainEntity(RequestFirmbankingJpaEntity requestFirmBankingRequest, UUID uuid) {
        return FirmbankingRequest.generateFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(requestFirmBankingRequest.getRequestFirmbankingId() + ""),
                new FirmbankingRequest.FromBankName(requestFirmBankingRequest.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(requestFirmBankingRequest.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(requestFirmBankingRequest.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(requestFirmBankingRequest.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(requestFirmBankingRequest.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(requestFirmBankingRequest.getFirmbankingStatus()),
                uuid
        );
    }
}
