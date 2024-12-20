package com.kjy.merchant.tenant;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

    private final DataSource dataSource;

    public TenantConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection(Object schema) throws SQLException {
        Connection connection = dataSource.getConnection();
        String tenantSchema = TenantContext.getCurrentTenant() != null ? TenantContext.getCurrentTenant() :  String.valueOf(schema);
        System.out.println("뭐로 연결3333333333333 :: >" +tenantSchema);
        connection.setSchema(String.valueOf(tenantSchema));
        return connection;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseConnection(Object o, Connection connection) throws SQLException {
        connection.setSchema("public");
        connection.close();
    }
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {

    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
