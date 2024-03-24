package com.msapay.membership.application.port.in;

import com.msapay.membership.domain.Membership;
import common.UseCase;
//@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command); // 커멘드를 받음으로서 인터페이스(구현체가) 리퀘스트를 커멘드로 바꾸고 커멘드가 usecase를 사용
}