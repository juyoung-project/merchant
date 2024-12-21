package com.kjy.merchant.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping(value = "/admin")
    public String admin() {
        if ( SecurityContextHolder.getContext() == null) {
            return "error_404";
        }
        return "admin";
    }

    @GetMapping(value = "/admin-login")
    public String adminLogin() {
        return "adminLogin";
    }
}
