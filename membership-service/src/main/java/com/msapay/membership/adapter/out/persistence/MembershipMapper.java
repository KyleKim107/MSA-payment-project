package com.msapay.membership.adapter.out.persistence;

import com.msapay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public Membership mapToDaminEntity(MembershipJpaEntity entity){
        return Membership.generateMember(
                new Membership.MembershipId(entity.getMembershipId())
                ,new Membership.MembershipName(entity.getName())
                ,new Membership.MembershipAddress(entity.getAddress())
                ,new Membership.MembershipEmail(entity.getEmail())
                ,new Membership.MembershipValid(entity.isValid())
                ,new Membership.MembershipCorp(entity.isCorp())
        );
    }
}
