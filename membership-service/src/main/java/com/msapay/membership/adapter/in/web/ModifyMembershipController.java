package com.msapay.membership.adapter.in.web;

import com.msapay.common.WebAdapter;
import com.msapay.membership.application.port.in.ModifyMembershipCommand;
import com.msapay.membership.application.port.in.ModifyMembershipUseCase;
import com.msapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;
    @PostMapping(path="/membership/modify")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    ResponseEntity<Membership> modifyMembershipByMemberId(@RequestBody ModifyMembershipRequest request){ // requset


        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(request.isValid())
                .isCorp(request.isCorp())
                .build();
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }
}
