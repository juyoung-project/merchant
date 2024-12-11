package com.kjy.merchant.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TenantDto {
    private String tenantName;
    private String status;
    private String targetTenant;
    private String sourceTenant;
}
