package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.dto.TenantDto;
import com.kjy.merchant.entity.Tenant;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.repository.TenantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class TenantManageService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private DataSource dataSource;

    @Transactional
    public void createTenant(TenantDto dto) {

        /**
         * 1. 테넌트 정보 db에 저장
         * 2. 테넌트명으로 스키마 생성
         * 3. public에 있는 모든 테이블 데이터 가지고오기 ( tenants table은 제외 )
         * 4. 복사 실행
         * */
        this.createSchema( dto.getTenantName() );
        this.copyPublicTenant( dto );
    }

    public List<Tenant> readTenant() {
       return tenantRepository.findTenantList();
    }


    // public 테이블에 구조만 복사
    private void copyPublicTenant(TenantDto dto){
        this._baseCopyTenant(dto, false);
    }

    // 특정 테넌트에 데이터 까지 복사
    @Transactional
    public void copyTenantWithData(TenantDto dto) {
        this.createSchema(dto.getTargetTenant());
        this._baseCopyTenant(dto, true);
    }

    private void _baseCopyTenant(TenantDto dto, boolean isWithData) {
        List<String> tables = this.getTableNames();
        for (String table : tables) {
            String copyTableQuery = getTableQuery(dto, isWithData, table);

            try (Statement statement = dataSource.getConnection().createStatement()) {
                statement.executeUpdate(copyTableQuery);
            } catch (SQLException e) {
                throw new BizException(Code.ERROR, "테넌트 생성시 오류가 발생했습니다", e);
            }
        }
    }

    private static String getTableQuery(TenantDto dto, boolean isWithData, String table) {
        String copyTableQuery;
        if (isWithData) {
            copyTableQuery = String.format(
                    "CREATE TABLE %s.%s AS TABLE %s.%s",
                    dto.getTargetTenant(), table, dto.getSourceTenant(),table
            );
        } else {
            copyTableQuery = String.format(
                    "CREATE TABLE %s.%s (LIKE public.%s INCLUDING ALL)",
                    dto.getTenantName(), table, table
            );
        }
        System.out.println(copyTableQuery);
        return copyTableQuery;
    }

    private void createSchema(String tenantName) {

        tenantName = tenantName.toLowerCase(); // 스키마 이름은 소문자로 통일

        // 테넌트 중복 확인
        if (tenantRepository.findByTenantName(tenantName).isPresent()) {
            throw new BizException(Code.ERROR ,"이미 존재하는 테넌트입니다 :: " + tenantName );
        }

        // 테넌트 엔티티 생성 및 저장
        Tenant tenant = new Tenant();
        tenant.setTenantName(tenantName);
        tenantRepository.save(tenant);

        String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS " + tenantName;
        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.executeUpdate(createSchemaQuery);
        } catch (SQLException e) {
           throw new BizException(Code.ERROR, "테넌트 생성시 오류가 발생했습니다");
        }
    }

    private List<String> getTableNames()  {
        List<String> tables = new ArrayList<>();
        String query = "SELECT tablename FROM pg_tables WHERE schemaname = 'public' and tablename != 'tenants' ";
        try (Statement statement = dataSource.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                tables.add(resultSet.getString("tablename"));
            }
        } catch (SQLException e) {
            throw new BizException(Code.ERROR, "테이블 정보를 가지고 오는중 오류가 발생했습니다.");
        }
        return tables;
    }

    public void deactivateTenant(TenantDto dto) {
        Tenant tenant = getTenant(dto);
        tenant.setStatus("INACTIVE");
        tenantRepository.save(tenant);
    }

    private Tenant getTenant(TenantDto dto) {
        return tenantRepository.findByTenantName(dto.getTenantName())
                .orElseThrow(() -> new BizException(Code.ERROR,"Tenant not found: " + dto.getTenantName()));
    }
}
