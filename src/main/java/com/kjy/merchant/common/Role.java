package com.kjy.merchant.common;

import lombok.Getter;

@Getter
public enum Role {
	
	STAFF("가맹점주"),
	HQ_STAFF("본사직원"),
	HQ_ADMIN("본사총관리자"),
	ADMIN("관리자");
	
	private String desc;
	
	private Role(String desc) {
		this.desc = desc;
	}
}
