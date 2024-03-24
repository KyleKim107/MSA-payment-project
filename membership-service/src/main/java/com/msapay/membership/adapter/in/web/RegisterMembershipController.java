package com.msapay.membership.adapter.in.web;

import common.WebAdapter;
import com.msapay.membership.application.port.in.RegisterMembershipCommand;
import com.msapay.membership.application.port.in.RegisterMembershipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;
    @PostMapping(path="membership/register")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    void registerMembership(@RequestBody RegisterMembershipRequest request){ // requset
        //usecase로 Request를 처리한다.
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isCorp(request.isCorp())
                .isValid(true)
                .build();
        registerMembershipUseCase.registerMembership(command);
    }
}
