package com.kjy.merchant.util;

import com.kjy.merchant.entity.Member;
import com.kjy.merchant.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberUtils {

	private static MemberRepository memberRepository;

	@Autowired
	public MemberUtils(MemberRepository memberRepository) {
		MemberUtils.memberRepository = memberRepository;
	}

	private MemberUtils(){};

	public static Member getCurrentMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String email = authentication.getName();
			return memberRepository.findByEmail(email).orElse(null);
		}
		return null;
	}
}
