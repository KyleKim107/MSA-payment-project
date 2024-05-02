package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.externalbank.BankAccount;
import com.msapay.banking.adapter.out.externalbank.GetBankAccountRequest;

public interface RequsetBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
