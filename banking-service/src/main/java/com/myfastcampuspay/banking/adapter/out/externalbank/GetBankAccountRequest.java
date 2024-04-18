package com.myfastcampuspay.banking.adapter.out.externalbank;

import lombok.Data;

@Data
public class GetBankAccountRequest {
    private String bankName;
    private String bankAccountNumber;

    public GetBankAccountRequest(String bankName, String bankAccountNumber) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
    }

}
