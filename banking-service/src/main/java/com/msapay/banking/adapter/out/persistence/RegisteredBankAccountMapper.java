package com.msapay.banking.adapter.out.persistence;

import com.msapay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

    public RegisteredBankAccount mapToDaminEntity(RegisteredBankAccountJpaEntity entity){
        return RegisteredBankAccount.generateRegisteredBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(entity.getRegisteredBankAccountId())
                ,new RegisteredBankAccount.MembershipId(entity.getMembershipId())
                ,new RegisteredBankAccount.BankName(entity.getBankName())
                ,new RegisteredBankAccount.BankAccountNumber(entity.getBankAccountNumber())
                ,new RegisteredBankAccount.LinkedStatusIsValid(entity.isLinkedStatusIsValid())
        );
    }
}
