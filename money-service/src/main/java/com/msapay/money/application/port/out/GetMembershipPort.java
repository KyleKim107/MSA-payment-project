package com.msapay.money.application.port.out;


public interface GetMembershipPort {
    MembershipStatus getMembership(String membershipId);
}
