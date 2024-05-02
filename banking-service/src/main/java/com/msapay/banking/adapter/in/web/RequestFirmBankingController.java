package com.msapay.banking.adapter.in.web;

import com.msapay.banking.application.port.in.RequestFirmbankingCommand;
import com.msapay.banking.application.port.in.RequestFirmbankingUseCase;
import com.msapay.banking.domain.FirmbankingRequest;
import com.msapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmbankingUseCase registerBankAccountUseCase;

    @PostMapping(path="/banking/firmbanking/request")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    FirmbankingRequest requestFirmbanking(@RequestBody RequestFirmBankingRequest request){ // requset
        //usecase로 Request를 처리한다.
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();


       return registerBankAccountUseCase.requestFirmbanking(command);
    }
}
