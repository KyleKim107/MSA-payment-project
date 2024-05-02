package com.msapay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity,Long> {
    @Query("select m from MemberMoneyJpaEntity m where m.membershipId = :membershipId")
    Optional<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);
}
