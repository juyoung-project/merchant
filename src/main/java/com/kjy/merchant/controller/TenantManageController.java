package com.kjy.merchant.controller;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.dto.MemberDto;
import com.kjy.merchant.dto.MerchantDto;
import com.kjy.merchant.dto.TenantDto;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.service.AdminService;
import com.kjy.merchant.service.TenantManageService;
import com.kjy.merchant.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TenantManageController {

    @Autowired
    private TenantManageService tenantManageService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/api/temp-create-admin")
    public ResponsePojo tempCreateAdmin() {
        adminService.createAdminUser();
        return ResponsePojo.success(null, "계정이 생성되었습니다.");
    }

    @PostMapping(value = "/api/create-tenant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponsePojo createTenant(@RequestBody TenantDto dto) {
        tenantManageService.createTenant(dto);
        return ResponsePojo.success(null,"생성완료");
    }

    @PostMapping(value = "/api/read-tenant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponsePojo readTenant(@RequestBody TenantDto dto) {
        return ResponsePojo.success(tenantManageService.readTenant(),"리스트 호출완료");
    }

    @PostMapping(value = "/api/deactivate-tenant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponsePojo deactivateTenant(@RequestBody TenantDto dto) {
        tenantManageService.deactivateTenant(dto);
        return ResponsePojo.success(null,"리스트 호출완료");
    }

    @PostMapping(value = "/api/copy-tenant-with-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponsePojo copyTenantWithData(@RequestBody TenantDto dto) {
        tenantManageService.copyTenantWithData(dto);
        return ResponsePojo.success(null,"리스트 호출완료");
    }
}
