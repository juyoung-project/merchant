package com.kjy.merchant.entity;

import com.kjy.merchant.common.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 증가 ID

    @Column(name = "username", nullable = false, length = 50)
    private String username; // 사용자 이름

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email; // 이메일 (고유)

    @Column(name = "password", nullable = false, length = 255)
    private String password; // 비밀번호 (암호화 필요)

    @Column(name = "contact_number", length = 15)
    private String contactNumber; // 연락처 (옵션)

    @Enumerated(EnumType.STRING )
    private Role role;

    @Builder.Default
    @Column(name = "status", nullable = false, length = 20)
    private String status = "ACTIVE"; // 상태 (기본값: ACTIVE)

    @Builder.Default
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime = LocalDateTime.now(); // 생성 시간

    @Column(name = "update_time")
    private LocalDateTime updateTime; // 수정 시간

    @Column(name = "create_member_id")
    private Long createMemberId; // 생성자 ID

    @Column(name = "update_member_id")
    private Long updateMemberId; // 수정자 ID

    @Builder.Default
    @Column(name = "del_yn", nullable = false, length = 1)
    private String delYn = "N"; // 삭제 여부 (기본값: N)
}