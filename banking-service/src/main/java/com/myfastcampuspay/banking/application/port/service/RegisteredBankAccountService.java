package com.myfastcampuspay.banking.application.port.service;

import com.msapay.common.UseCase;

import com.myfastcampuspay.banking.adapter.out.externalbank.BankAccount;
import com.myfastcampuspay.banking.adapter.out.externalbank.GetBankAccountRequest;
import com.myfastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.myfastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.myfastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.myfastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.myfastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.myfastcampuspay.banking.application.port.out.RequsetBankAccountInfoPort;
import com.myfastcampuspay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisteredBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort; // DB접근을 위한 포트
    private final RegisteredBankAccountMapper mapper;
    private  final RequsetBankAccountInfoPort requsetBankAccountInfoPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 은행 계좌를 등록해야하는 상황
        //1. 등록된 계좌인지 확인한다
        //외부의 은행에 이 계좌 정상인지 확인한다
        // biz logic -> external system
        // Port -> Adapter -> external system
        // Port ->


        //시제 외부의 은행계좌 정보를 Get
        BankAccount accountInfo = requsetBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean isValid = accountInfo.isLinkedStatusIsValid();

        //2. 등록 가능한계좌라면 등록한다. 성공하면 등록에 성공한 정보를 리턴
        //2-1 등록 가능하지 않은 계좌라면 에러를 리턴.
        if(isValid){
            //등록정보 저장
            RegisteredBankAccountJpaEntity bankAccountJpa = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId())
                    ,new RegisteredBankAccount.BankName(command.getBankName())
                    ,new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber())
                    ,new RegisteredBankAccount.LinkedStatusIsValid(isValid));
            return mapper.mapToDaminEntity(bankAccountJpa) ;
        }else{
            return null;
        }

    }
}
