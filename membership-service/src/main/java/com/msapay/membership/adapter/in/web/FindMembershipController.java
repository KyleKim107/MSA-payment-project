package com.msapay.membership.adapter.in.web;

import com.msapay.membership.application.port.in.FindMembershipCommand;
import com.msapay.membership.domain.Membership;
import com.msapay.common.WebAdapter;
import com.msapay.membership.application.port.in.FindMembershipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;
    @GetMapping(path="/membership/{membershipId}")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    ResponseEntity<Membership> findMembershipById(@PathVariable Long membershipId){ // requset
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }
}
