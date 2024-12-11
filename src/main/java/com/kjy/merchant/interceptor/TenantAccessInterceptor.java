package com.kjy.merchant.interceptor;

import com.kjy.merchant.entity.Tenant;
import com.kjy.merchant.exception.TenantNotFoundException;
import com.kjy.merchant.repository.TenantRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantAccessInterceptor implements HandlerInterceptor {

    private static final String DEFAULT_TENANT = "public";

    @Autowired
    private TenantRepository tenantRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 서버 이름에서 서브도메인 추출
        String serverName = request.getServerName();
        String subdomain = extractSubdomain(serverName);

        if (subdomain == null || DEFAULT_TENANT.equals(subdomain)) {
            return true;
        }

        // 데이터베이스에서 테넌트 상태 확인
        Tenant tenant = tenantRepository.findByTenantName(subdomain).orElse(null);
        if (tenant == null || "INACTIVE".equals(tenant.getStatus())) {
            throw new TenantNotFoundException("찾을수 없는 도메인 입니다");
        }

        return true; // 활성화된 테넌트인 경우 요청 계속 처리
    }

    private String extractSubdomain(String host) {
        if (host == null || !host.contains(".")) {
            return null;
        }
        String[] parts = host.split("\\.");
        return parts.length > 2 ? parts[0] : null;
    }
}