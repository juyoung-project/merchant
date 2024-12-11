package com.kjy.merchant.controller;

import com.kjy.merchant.exception.TenantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(TenantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTenantNotFoundException(TenantNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_404";
    }
    
    // BizException은 추후 프론트단에서 alert경고창으로 조절

}
