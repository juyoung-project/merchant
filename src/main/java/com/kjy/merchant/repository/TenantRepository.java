package com.kjy.merchant.repository;

import com.kjy.merchant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByTenantName(String tenantName);

    @Query(value = "SELECT * FROM public.tenants t WHERE t.status='ACTIVE'", nativeQuery = true)
    List<Tenant> findTenantList();

}
