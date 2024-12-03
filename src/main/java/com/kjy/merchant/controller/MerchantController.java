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

    @PostMapping(value = "/api/read-merchant")
    public ResponsePojo readMerchant(@RequestBody MerchantDto dto) {
        return ResponsePojo.success( merchantService.readMerchant(dto),"가맹점리스트를 정상적으로 불러왔습니다.");
    }

    @PostMapping(value = "/api/update-merchant")
    public ResponsePojo updateMerchant(@RequestBody MerchantDto dto) {
        merchantService.updateMerchant(dto);
        return ResponsePojo.success(null ,"가맹점 업데이트 정상적으로 실행되었습니다.");
    }

    @PostMapping(value = "/api/delete-merchant")
    public ResponsePojo deleteMerchant(@RequestBody MerchantDto dto) {
        merchantService.deleteMerchant(dto);
        return ResponsePojo.success( null,"가맹점이 삭제되었습니다.");
    }

}
