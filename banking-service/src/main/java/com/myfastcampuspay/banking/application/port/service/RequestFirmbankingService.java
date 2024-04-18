package com.myfastcampuspay.banking.application.port.service;

import com.msapay.common.UseCase;
import com.myfastcampuspay.banking.adapter.in.web.RequestFirmBankingRequest;
import com.myfastcampuspay.banking.adapter.out.externalbank.ExternalFirmbankingRequest;
import com.myfastcampuspay.banking.adapter.out.externalbank.FirmbankingResult;
import com.myfastcampuspay.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.myfastcampuspay.banking.adapter.out.persistence.RequestFirmbankingMapper;
import com.myfastcampuspay.banking.application.port.in.RequestFirmbankingCommand;
import com.myfastcampuspay.banking.application.port.in.RequestFirmbankingUseCase;
import com.myfastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.myfastcampuspay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.myfastcampuspay.banking.application.port.out.RequestFirmbankingPort;
import com.myfastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase {
    private final RequestFirmbankingMapper firmbankingRequestMapper;
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        // business logic
        // a bank-> b bank
        // 1.요청에 대해 정보를 먼저 write, "요청" 상태로
        RequestFirmbankingJpaEntity requestedEntity = requestFirmbankingPort.createRequestFirmbanking(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0)
        );
        // 2.외부 은행에 펌뱅킹 요청
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        command.getFromBankName()
                        , command.getFromBankAccountNumber()
                        , command.getToBankName()
                        , command.getToBankAccountNumber()
        ));

        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID.toString());
        // 3.결과에 따라서 1번에서 작성했던 FirmbankingRequest를 업데이트
        if(result.getResultCode() == 0){
            requestedEntity.setFirmbankingStatus(1);
        }else{
            requestedEntity.setFirmbankingStatus(2);
        }
        // 4.결과를 리턴
        return firmbankingRequestMapper.mapToDomainEntity(requestFirmbankingPort.modifyRequestFirmbanking(requestedEntity), randomUUID);
    }
}
