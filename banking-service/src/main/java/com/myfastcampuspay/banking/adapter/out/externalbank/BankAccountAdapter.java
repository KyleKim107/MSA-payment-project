package com.myfastcampuspay.banking.adapter.out.externalbank;

import com.msapay.common.ExternalSystemAdapter;
import com.myfastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountRepository;
import com.myfastcampuspay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.myfastcampuspay.banking.application.port.out.RequsetBankAccountInfoPort;
import com.myfastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequsetBankAccountInfoPort, RequestExternalFirmbankingPort {

    private final RegisteredBankAccountRepository bankAccountRepository;
    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        // 실제외부 은행에 http 통신을 통해 정보를 가져온다
        // 실제 은행계좌 정보를 가져오고
        // 실제 은행계좌 -> BankAccount

        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {
        // 실제로 외부 은행에 http 통신을 통해서 펌뱅킹 요청을 한다
        // 그 결과를 받고 파싱해서
        // firmbankingResult로 받는다.
        FirmbankingResult result = new FirmbankingResult(0);

        return new FirmbankingResult(0);
    }
}
