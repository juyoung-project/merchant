package com.kjy.merchant.controller;

import com.kjy.merchant.common.JwtTokenProvider;
import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.util.CookieUtils;
import com.kjy.merchant.util.MemberUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println( jwtTokenProvider.getRoleFromToken(CookieUtils.getJwtFromRequest(req, CookieUtils.JWT_COOKIE)) );
        System.out.println(MemberUtils.getCurrentMember().getId());
        return ResponsePojo.success(null, "check-token");
    }


}
