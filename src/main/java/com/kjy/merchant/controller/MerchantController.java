package com.kjy.merchant.controller;

import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.dto.MerchantDto;
import com.kjy.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping(value = "/api/create-merchant")
    public ResponsePojo createMerchant(@RequestBody MerchantDto dto) {
        merchantService.createMerchant(dto);
        return ResponsePojo.success(null,"생성완료");
    }

}
