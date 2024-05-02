package com.msapay.banking.application.port.out;

import com.msapay.banking.adapter.out.service.MembershipStatus;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
