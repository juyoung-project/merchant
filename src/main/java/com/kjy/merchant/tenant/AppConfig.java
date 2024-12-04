package com.kjy.merchant.tenant;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
public class AppConfig {

    @Bean public RequestContextFilter requestContextFilter() { return new RequestContextFilter();}
}
