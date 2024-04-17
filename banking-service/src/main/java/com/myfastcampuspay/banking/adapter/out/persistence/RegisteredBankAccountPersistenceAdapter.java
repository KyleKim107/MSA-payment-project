package com.myfastcampuspay.banking.adapter.out.persistence;

import com.msapay.common.PersistenceAdapter;
import com.myfastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.myfastcampuspay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {
    private final RegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId
            , RegisteredBankAccount.BankName bankName
            , RegisteredBankAccount.BankAccountNumber bankAccountNumber
            , RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return registeredBankAccountRepository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.getMembershipId()
                        ,bankName.getBankName()
                        ,bankAccountNumber.getBankAccountNumber()
                        ,linkedStatusIsValid.isLinkedStatus())
        );
    }

}
