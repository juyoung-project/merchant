package com.kjy.merchant.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TenantConnectionProvider connectionProvider;

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.kjy.merchant.entity");

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.multiTenancy", "SCHEMA");
        jpaProperties.put("hibernate.multi_tenant_connection_provider", connectionProvider);
        jpaProperties.put("hibernate.tenant_identifier_resolver", tenantIdentifierResolver);

        factoryBean.setJpaPropertyMap(jpaProperties);
        return factoryBean;
    }
}