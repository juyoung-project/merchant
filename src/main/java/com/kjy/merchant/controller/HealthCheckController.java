package com.kjy.merchant.controller;

import com.kjy.merchant.common.ResponsePojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HealthCheckController {

    @GetMapping(value = "/api/health-check")
    public ResponsePojo<HashMap<String, Object>> healthCheck() {
        return ResponsePojo.success(null, "check");
    }

}
