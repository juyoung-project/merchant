package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.entity.Member;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadBYYY");
        System.out.println(email);
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BizException(Code.ERROR, "해당계정이 존재하지 않습니다."));
        return User.withUsername(member.getEmail()).password(member.getPassword()).roles(member.getRole().name()).build();
    }
}
