package com.kjy.merchant.config;

import com.kjy.merchant.interceptor.TenantAccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TenantAccessInterceptor tenantAccessInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("http://*.localhost.com:8081")
                .allowedMethods("GET","POST")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 모든 요청에 대해 테넌트 상태 확인
        registry.addInterceptor(tenantAccessInterceptor).addPathPatterns("/**");
    }
}