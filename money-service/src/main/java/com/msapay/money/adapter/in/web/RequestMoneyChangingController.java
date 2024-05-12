package com.msapay.money.adapter.in.web;

import com.msapay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.msapay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.msapay.money.domain.MoneyChangingRequest;
import com.msapay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter // web Adapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

    @PostMapping(path="/money/increase")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreasingMoneyChangingRequest request){ // requset
        //usecase로 Request를 처리한다.
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);
        //moneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                1,0,
                moneyChangingRequest.getChangingMoneyAmount()
        );
       return resultDetail;
    }
    @PostMapping(path="/money/increase-async")//
    MoneyChangingResultDetail increaseMoneyChangingRequestAsync(@RequestBody IncreasingMoneyChangingRequest request){ // requset
        //usecase로 Request를 처리한다.
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequestAsync(command);
        //moneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                1,0,
                moneyChangingRequest.getChangingMoneyAmount()
        );
        return resultDetail;
    }
    @PostMapping(path="/money/decrease")// 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    MoneyChangingResultDetail decreaseMoneyChangingRequest(@RequestBody DecreasingMoneyChangingRequest request){ // requset
        //usecase로 Request를 처리한다.
//        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
//                .targetMembershipId(request.getTargetMembershipId())
//                .amount(request.getAmount())
//                .build();
//
//        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);
//        //moneyChangingRequest -> MoneyChangingResultDetail
//        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
//                moneyChangingRequest.getMoneyChangingRequestId(),
//                1,0,
//                moneyChangingRequest.getChangingMoneyAmount()
//        );
//        return resultDetail;
        return null;
    }
}
