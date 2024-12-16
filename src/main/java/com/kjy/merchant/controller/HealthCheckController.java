package com.kjy.merchant.controller;

import com.kjy.merchant.common.JwtTokenProvider;
import com.kjy.merchant.common.ResponsePojo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HealthCheckController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/api/health-check")
    public ResponsePojo<HashMap<String, Object>> healthCheck() {
        return ResponsePojo.success(null, "check");
    }
    @GetMapping(value = "/api/health-check-token")
    public ResponsePojo<HashMap<String, Object>> healthCheckToken(HttpServletRequest req) {
        return ResponsePojo.success(null, "check-token");
    }


}
