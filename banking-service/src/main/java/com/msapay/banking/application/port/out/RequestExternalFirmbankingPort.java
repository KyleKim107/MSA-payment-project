package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.externalbank.ExternalFirmbankingRequest;
import com.msapay.banking.adapter.out.externalbank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
