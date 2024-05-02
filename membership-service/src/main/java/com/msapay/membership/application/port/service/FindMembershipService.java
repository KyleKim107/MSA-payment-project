package com.msapay.membership.application.port.service;

import com.msapay.membership.adapter.out.persistence.MembershipMapper;
import com.msapay.membership.application.port.in.FindMembershipCommand;
import com.msapay.membership.domain.Membership;
import com.msapay.common.UseCase;
import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msapay.membership.application.port.in.FindMembershipUseCase;
import com.msapay.membership.application.port.out.FindMembershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {
//    private final FindMembershipPort
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDaminEntity(entity);
    }
}
