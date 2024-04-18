package com.myfastcampuspay.banking.application.port.out;

import com.myfastcampuspay.banking.adapter.out.externalbank.ExternalFirmbankingRequest;
import com.myfastcampuspay.banking.adapter.out.externalbank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
