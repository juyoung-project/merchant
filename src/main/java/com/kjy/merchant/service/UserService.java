package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.dto.MemberDto;
import com.kjy.merchant.entity.Member;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signUp(MemberDto dto)  {
        // sign up :: >> email, pw, role, username
        Member member = Member.builder()
                        .email(dto.getEmail())
                        .password( passwordEncoder.encode( dto.getPassword() ))
                        .role(dto.getRole())
                        .username(dto.getUsername())
                        .contactNumber(dto.getContactNumber())
                        .build();

        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new BizException(Code.ERROR, "계정생성중 오류가 발생했어요");
        }
    }

    public Member getMemberByEmail(String email) {
       return memberRepository.findByEmail(email).orElseThrow(() -> new BizException(Code.ERROR, "해당계정이 존재하지 않습니다."));
    }

}
