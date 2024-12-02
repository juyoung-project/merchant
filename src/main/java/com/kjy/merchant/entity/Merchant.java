package com.kjy.merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // 가맹점 ID (PK)

    @Column(name = "name", nullable = false, length = 100)
    private String name;  // 가맹점 이름

    @Column(name = "owner", nullable = false, length = 50)
    private String owner;  // 가맹점주 이름

    @Column(name = "member_id", nullable = false)
    private Long memberId;  // 가맹점주 ID (FK)

    @Column(name = "business_number", length = 20)
    private String businessNumber;  // 사업자등록번호 (고유)

    @Column(name = "contact_number", length = 15)
    private String contactNumber;  // 전화번호

    @Column(name = "address", nullable = false, length = 255)
    private String address;  // 전체 주소

    @Column(name = "address_si", nullable = false, length = 50)
    private String addressSi;  // 주소 (시)

    @Column(name = "address_gu", nullable = false, length = 50)
    private String addressGu;  // 주소 (구)

    @Builder.Default
    @Column(name = "status", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private String status = "ACTIVE";  // 가맹점 상태 (ACTIVE, INACTIVE)

    @Column(name = "open_date")
    private LocalDate openDate;  // 오픈일

    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;  // 계약 만료일

    @Builder.Default
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime = LocalDateTime.now();  // 생성 시간

    @Builder.Default
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime = LocalDateTime.now();  // 수정 시간

    @Column(name = "create_member_id")
    private Long createMemberId;  // 생성자 멤버 ID

    @Column(name = "update_member_id")
    private Long updateMemberId;  // 수정자 멤버 ID

    @Builder.Default
    @Column(name = "del_yn", nullable = false, length = 1)
    private String delYn = "N"; // 삭제 여부 (기본값: N)

}
