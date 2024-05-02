package com.msapay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
public class RegisteredBankAccount {
    // Membership 클래스는 오염이 되면 안되는 클래스, 핵심 도메인
    // field에 final을 붙이면 생성자에 의해 생성될때 주입받은 값 외로 변경 불가능하다.
    private final Long registeredBankAccountId;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;

    public static RegisteredBankAccount generateRegisteredBankAccount(
            RegisteredBankAccount.RegisteredBankAccountId AccountId
            , RegisteredBankAccount.MembershipId membershipId
            ,RegisteredBankAccount.BankName bankName
            ,RegisteredBankAccount.BankAccountNumber bankAccountNumber
            ,RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    ){
        return new RegisteredBankAccount(AccountId.id
                ,membershipId.membershipId
                ,bankName.bankName
                ,bankAccountNumber.bankAccountNumber
                ,linkedStatusIsValid.linkedStatus
        );
    }

    @Value
    public static class RegisteredBankAccountId{
        Long id;
        public RegisteredBankAccountId(Long value){
            this.id = value;
        }
    }
    @Value
    public static class MembershipId{
        String membershipId;
        public MembershipId(String membershipId){
            this.membershipId = membershipId;
        }
    }
    @Value
    public static class BankName{
        String bankName;
        public BankName(String bankName){
            this.bankName = bankName;
        }
    }
    @Value
    public static class BankAccountNumber{
        String bankAccountNumber;
        public BankAccountNumber(String bankAccountNumber){
            this.bankAccountNumber = bankAccountNumber;
        }
    }
    @Value
    public static class LinkedStatusIsValid {
        boolean linkedStatus;
        public LinkedStatusIsValid(boolean linkedStatus){
            this.linkedStatus = linkedStatus;
        }
    }

}
