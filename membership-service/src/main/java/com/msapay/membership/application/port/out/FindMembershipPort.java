package com.msapay.membership.application.port.out;

import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msapay.membership.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(Membership.MembershipId membershipId);
}
