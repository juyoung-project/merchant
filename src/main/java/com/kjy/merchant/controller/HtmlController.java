package com.kjy.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }
}
