package com.kjy.merchant.filter;

import com.kjy.merchant.entity.Tenant;
import com.kjy.merchant.exception.TenantNotFoundException;
import com.kjy.merchant.repository.TenantRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    @Autowired
    private TenantRepository tenantRepository;

    private static final String DEFAULT_TENANT = "public";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 서버 이름에서 서브도메인 추출
        String serverName = request.getServerName();
        String subdomain = extractSubdomain(serverName);
        if (subdomain == null || DEFAULT_TENANT.equals(subdomain)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 테넌트 확인
        Tenant tenant = tenantRepository.findByTenantName(subdomain).orElse(null);
        if (tenant == null || "INACTIVE".equals(tenant.getStatus())) {
            throw new TenantNotFoundException("찾을 수 없는 도메인입니다.");
        }
        System.out.println("333333333333333333333333");
        System.out.println(tenant.getTenantName());
        filterChain.doFilter(request, response); // 다음 필터로 전달
    }

    private String extractSubdomain(String host) {
        if (host == null || !host.contains(".")) {
            return null;
        }
        String[] parts = host.split("\\.");
        return parts.length > 2 ? parts[0] : null;
    }
}