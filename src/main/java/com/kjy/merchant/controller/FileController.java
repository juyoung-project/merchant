package com.kjy.merchant.controller;

import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.dto.MemberDto;
import com.kjy.merchant.dto.TenantDto;
import com.kjy.merchant.service.ExcelMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private ExcelMigrationService excelMigrationService;

    @PostMapping(value = "/api/upload-excel-migration-member")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponsePojo uploadExcelMigrationMember(  @RequestParam("drmType") String drmType,
                                                     @RequestParam("tenantType") String tenantType,
                                                     @RequestParam("file") MultipartFile file) {
        excelMigrationService.migrateMember(drmType, tenantType, file);
        return ResponsePojo.success(null,"업로드 완료");
    }
}
