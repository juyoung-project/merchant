package com.kjy.merchant.dto;

import com.kjy.merchant.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MemberDto {
    private String email;
    private String password;
    private Role role;
    private String username;
    private String contactNumber;
}
