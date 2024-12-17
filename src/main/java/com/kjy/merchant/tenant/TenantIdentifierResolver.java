package com.kjy.merchant.tenant;

import com.kjy.merchant.entity.Tenant;
import com.kjy.merchant.exception.TenantNotFoundException;
import com.kjy.merchant.repository.TenantRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT = "public";
    private final HttpServletRequest request;

    public TenantIdentifierResolver(HttpServletRequest request) {
        this.request = request;
    }

    // Hibernate가 데이터베이스 작업을 수행하기 전에 호출
    @Override
    public Object resolveCurrentTenantIdentifier() {

        // 처음 Spring Run 될때에는 request.getServerName()에 값을 알 수 없어 기본 테넌트 처리함
        if (RequestContextHolder.getRequestAttributes() == null) {
            return DEFAULT_TENANT;
        } else {
            String serverName = request.getServerName();
            System.out.println("===============================================");
            System.out.println(serverName);
            return getSubdomain(serverName) != null ? getSubdomain(serverName) : DEFAULT_TENANT;
        }

    }

    // Hibernate 세션이 테넌트 변경을 허용할지 여부를 결정 기본적으로 true 설정으로 함
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    private String getSubdomain(String host) {
        if (host == null || !host.contains(".")) {
            return null;
        }
        String[] hosts = host.split("\\.");
        return hosts.length > 2 ? hosts[0] : null;
    }
}
