package com.kjy.merchant.controller;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.common.JwtTokenProvider;
import com.kjy.merchant.common.ResponsePojo;
import com.kjy.merchant.dto.MemberDto;
import com.kjy.merchant.entity.Member;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.service.UserService;
import com.kjy.merchant.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/api/sign-up")
    public ResponsePojo signUp(@RequestBody MemberDto dto) {
        userService.signUp(dto);
        return ResponsePojo.success(null, "계정이 생성되었습니다.");
    }

    @PostMapping(value = "/api/sign-in")
    public ResponsePojo signIn(@RequestBody MemberDto dto, HttpServletRequest request, HttpServletResponse response ) {
        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Map<String, String> tokenMap = new HashMap<String, String>();
        try {
            authenticationManager.authenticate(authenticationToken);
            Member mem = userService.getMemberByEmail(dto.getEmail());
            String jwtToken = jwtTokenProvider.generateToken( mem.getEmail(), mem.getRole());
            String refreshToken = jwtTokenProvider.generateRefreshToken(mem.getEmail(), mem.getRole());

            tokenMap.put("jwtToken", jwtToken);
            tokenMap.put("reJwtToken", refreshToken);

        } catch (Exception e) {
            throw new BizException(Code.ERROR, "정보가 올바르지 않습니다.", e);
        }
        return ResponsePojo.success(tokenMap, "로그인 성공");
    }
}
