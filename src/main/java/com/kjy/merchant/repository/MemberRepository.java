package com.kjy.merchant.repository;

import com.kjy.merchant.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional< Member > findByEmail(String email);
}
