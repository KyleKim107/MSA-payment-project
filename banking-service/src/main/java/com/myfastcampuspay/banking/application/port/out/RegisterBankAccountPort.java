package com.myfastcampuspay.banking.application.port.out;


import com.myfastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.myfastcampuspay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
             RegisteredBankAccount.MembershipId membershipId
            , RegisteredBankAccount.BankName bankName
            , RegisteredBankAccount.BankAccountNumber bankAccountNumber
            ,  RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
