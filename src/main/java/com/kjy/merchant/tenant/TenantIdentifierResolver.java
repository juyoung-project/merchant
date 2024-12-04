package com.kjy.merchant.tenant;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT = "public";
    private final HttpServletRequest request;
//    private final TenantRepository tenantRepository;

    public TenantIdentifierResolver(HttpServletRequest request) {
        this.request = request;
//        this.tenantRepository = tenantRepository;
    }

    // Hibernate가 데이터베이스 작업을 수행하기 전에 호출
    @Override
    public Object resolveCurrentTenantIdentifier() {
        System.out.println(request);
//        String host = request.getServerName();
//        String subdomain = this.getSubdomain(host);
        return DEFAULT_TENANT;
//        if (subdomain == null) {
//            return DEFAULT_TENANT;
//        } else {
//            return  subdomain;
//        }

//        Tenant tenant = tenantRepository.findBySubdomain(subdomain);
//        return subdomain != null ? tenant.getSchemaName() : DEFAULT_TENANT;
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
