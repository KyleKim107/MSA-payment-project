package com.msapay.membership.application.port.out;

import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msapay.membership.domain.Membership;

public interface RegisterMembershipPort {
    MembershipJpaEntity createMembership(
             Membership.MembershipName name
            , Membership.MembershipAddress address
            , Membership.MembershipEmail email
            , Membership.MembershipValid valid
            , Membership.MembershipCorp corp  );

}
