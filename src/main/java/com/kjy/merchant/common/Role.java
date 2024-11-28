package com.kjy.merchant.common;

import lombok.Getter;

@Getter
public enum Role {
	
	STAFF("가맹점주"),
	ADMIN("관리자");
	
	private String desc;
	
	private Role(String desc) {
		this.desc = desc;
	}
}
