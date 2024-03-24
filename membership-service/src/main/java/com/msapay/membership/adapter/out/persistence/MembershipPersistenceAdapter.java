package com.msapay.membership.adapter.out.persistence;

import com.msapay.membership.application.port.out.FindMembershipPort;
import common.PersistenceAdapter;
import com.msapay.membership.application.port.out.RegisterMembershipPort;
import com.msapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {
    private final MembershipRepository membershipRepository;
    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName name, Membership.MembershipAddress address, Membership.MembershipEmail email, Membership.MembershipValid valid, Membership.MembershipCorp corp) {
       return membershipRepository.save(
               new MembershipJpaEntity(
                       name.getName()
                       , address.getAddress()
                       , email.getEmail()
                       , valid.isValid()
                       , corp.isCorp())
       );
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.getById(membershipId.getId());
    }
}
