package com.myfastcampuspay.banking.application.port.in;

import com.myfastcampuspay.banking.adapter.in.web.RequestFirmBankingRequest;
import com.myfastcampuspay.banking.domain.FirmbankingRequest;

//@UseCase
public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command); // 커멘드를 받음으로서 인터페이스(구현체가) 리퀘스트를 커멘드로 바꾸고 커멘드가 usecase를 사용
}
