package com.students.webappwithsecurity.service;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

@Service
public class LogsStoreConnectionFactory
{
    private static interface Singleton {
        final LogsStoreConnectionFactory INSTANCE = new LogsStoreConnectionFactory();
    }
    private final HikariPool hikariPool;
    private LogsStoreConnectionFactory() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:./testdb/students");
        dataSourceBuilder.password("");
        dataSourceBuilder.username("");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setDataSource(dataSourceBuilder.build());
        hikariPool = new HikariPool(hikariConfig);
    }
    public static Connection getConnectionLog() {
        try {
            return Singleton.INSTANCE.hikariPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}