package com.msapay.banking.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registered_bank_account")
@Data
@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {
    @Id
    @GeneratedValue
    private  Long registeredBankAccountId;
    private  String membershipId;
    private  String bankName; // ENUM
    private  String bankAccountNumber;
    private  boolean linkedStatusIsValid;

    public RegisteredBankAccountJpaEntity(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;

    }
}
