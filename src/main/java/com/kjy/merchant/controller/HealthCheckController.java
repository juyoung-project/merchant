package com.kjy.merchant.controller;

import com.kjy.merchant.common.ResponsePojo;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController(value = "/api/health-check")
public class HealthCheckController {

    public ResponsePojo<HashMap<String, Object>> healthCheck() {
        return ResponsePojo.success(null, "check");
    }

}
