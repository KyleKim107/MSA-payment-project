package com.msapay.membership.application.port.service;

import com.msapay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.msapay.membership.adapter.out.persistence.MembershipMapper;
import com.msapay.membership.application.port.in.RegisterMembershipCommand;
import com.msapay.membership.application.port.in.RegisterMembershipUseCase;
import com.msapay.membership.domain.Membership;
import com.msapay.common.UseCase;
import com.msapay.membership.application.port.out.RegisterMembershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership registerMembership(RegisterMembershipCommand command){
        // command -> DB

        //buisiness logic -> DB
        // needs to go external system
        // port -> adapter
        MembershipJpaEntity entity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipValid(command.isValid()),
                new Membership.MembershipCorp(command.isCorp())
        );
        // entity => Membership
        return membershipMapper.mapToDaminEntity(entity);
    }

}
