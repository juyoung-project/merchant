package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.common.Role;
import com.kjy.merchant.entity.Member;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createAdminUser() {
        Member member = Member.builder()
                .email("ADMIN")
                .password( passwordEncoder.encode( "ADMIN" ))
                .role(Role.ADMIN)
                .username("관리자")
                .contactNumber("01012345678")
                .build();

        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new BizException(Code.ERROR, "계정생성중 오류가 발생했어요", e);
        }
    }

}
