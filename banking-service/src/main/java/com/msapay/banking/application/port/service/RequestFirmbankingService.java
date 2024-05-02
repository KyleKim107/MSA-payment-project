package com.msapay.banking.application.port.service;

import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUseCase;
import com.msapay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.msapay.banking.application.port.out.RequestFirmbankingPort;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.common.UseCase;
import com.msapay.banking.adapter.out.externalbank.ExternalFirmbankingRequest;
import com.msapay.banking.adapter.out.externalbank.FirmbankingResult;
import com.msapay.banking.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.msapay.banking.adapter.out.persistence.RequestFirmbankingMapper;
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
        // 2.Request external firmbanking to validate the account
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
            requestedEntity.setFirmbankingStatus(1);// 완료
        }else{
            requestedEntity.setFirmbankingStatus(2);// 실패
        }
        // 4.결과를 리턴
        return firmbankingRequestMapper.mapToDomainEntity(requestFirmbankingPort.modifyRequestFirmbanking(requestedEntity), randomUUID);
    }
}
