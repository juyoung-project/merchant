package com.kjy.merchant.controller;

import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.dto.MerchantDto;
import com.kjy.merchant.dto.TenantDto;
import com.kjy.merchant.service.TenantManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantManageController {

    @Autowired
    private TenantManageService tenantManageService;

    @PostMapping(value = "/api/create-tenant")
    public ResponsePojo createTenant(@RequestBody TenantDto dto) {
        tenantManageService.createTenant(dto);
        return ResponsePojo.success(null,"생성완료");
    }
    @PostMapping(value = "/api/read-tenant")
    public ResponsePojo readTenant(@RequestBody TenantDto dto) {
        return ResponsePojo.success(tenantManageService.readTenant(),"리스트 호출완료");
    }

    @PostMapping(value = "/api/deactivate-tenant")
    public ResponsePojo deactivateTenant(@RequestBody TenantDto dto) {
        tenantManageService.deactivateTenant(dto);
        return ResponsePojo.success(null,"리스트 호출완료");
    }

    @PostMapping(value = "/api/copy-tenant-with-data")
    public ResponsePojo copyTenantWithData(@RequestBody TenantDto dto) {
        tenantManageService.copyTenantWithData(dto);
        return ResponsePojo.success(null,"리스트 호출완료");
    }
}
