package com.kjy.merchant.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class MerchantDto {
    private Long id;  // 가맹점 ID
    private String name;  // 가맹점 이름
    private String owner;  // 가맹점주 이름
    private Integer memberId;  // 가맹점 관리자 ID
    private String businessNumber;  // 사업자등록번호
    private String contactNumber;  // 전화번호
    private String address;  // 전체 주소
    private String addressSi;  // 주소 (시)
    private String addressGu;  // 주소 (구)
    private String status;  // 가맹점 상태
    private LocalDate openDate;  // 오픈일
    private LocalDate contractEndDate;  // 계약 만료일
    private LocalDateTime createTime;  // 생성 시간
    private LocalDateTime updateTime;  // 수정 시간
    private Integer createMemberId;  // 생성자 멤버 ID
    private Integer updateMemberId;  // 수정자 멤버 ID
    private String delYn;  // 삭제 여부
}
