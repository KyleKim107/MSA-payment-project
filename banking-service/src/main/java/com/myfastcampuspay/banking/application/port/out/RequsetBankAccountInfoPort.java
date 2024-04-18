package com.myfastcampuspay.banking.application.port.out;

import com.myfastcampuspay.banking.adapter.out.externalbank.BankAccount;
import com.myfastcampuspay.banking.adapter.out.externalbank.GetBankAccountRequest;

public interface RequsetBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
