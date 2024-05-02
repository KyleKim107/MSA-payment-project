package com.msapay.banking.adapter.in.web;

import com.msapay.common.WebAdapter;
import com.msapay.banking.application.port.in.RegisterBankAccountCommand;
import com.msapay.banking.application.port.in.RegisterBankAccountUseCase;
import com.msapay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path="/banking/account/register")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisteredBankAccountRequest request){ // requset
        //usecase로 Request를 처리한다.
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();
        RegisteredBankAccount regusterBankAccount = registerBankAccountUseCase.registerBankAccount(command);
        if(regusterBankAccount == null){
            return null;
        }
       return regusterBankAccount;
    }
}
