package com.msapay.banking.adapter.out.externalbank;

import lombok.Data;

@Data
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;
    private boolean linkedStatusIsValid;

    public BankAccount(String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public boolean isLinkedStatusIsValid() {
        return linkedStatusIsValid;
    }
}
