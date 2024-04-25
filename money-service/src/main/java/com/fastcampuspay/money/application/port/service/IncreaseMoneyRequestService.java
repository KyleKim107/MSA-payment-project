package com.fastcampuspay.money.application.port.service;

import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import com.msapay.common.UseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;
    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        //머니 충전, 증액이라는 과정
        // 1 고객 정보가 정상인지 확인 (membership)

        // 2 고객의 연동된 계좌가 있는지 그리고 정상적인지 확인 (banking)

        // 3. 법인계좌 상태도 정상인지 확인 (banking)

        // 4. 증액을 위한 기록 MoneyChangingRequest (moneyChaningRequest)

        // 5. 펌뱅킹 수행(고객의 연동된 계좌 -> 패캠페이 법인 계좌) (banking)

        // 6-1 결과가 정상이라면 성공이라고 MoneyChangingRequest 상태값 변경후 리턴
        // 성공시 membership의 money를 증액
        MemberMoneyJpaEntity entity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MemberMoneyId(command.getTargetMembershipId())
                , command.getAmount()
        );
        if (entity != null) {
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            new MoneyChangingRequest.MoneyChangingType(1),
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            new MoneyChangingRequest.MoneyChangingStatus(1),
                            new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                    )
            );
        }

        // 6-2 결과가 비정상이라면 실패라고 MoneyChangingRequest 상태값 변경후 리턴
        return null;
    }
}
