package com.students.webappwithsecurity.config;

import com.students.webappwithsecurity.service.LogMessageService;
import com.students.webappwithsecurity.service.MakeLogMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MainConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String name;
    @Value("${spring.datasource.password}")
    private String password;
    @Bean(name = "h2DataSource")
    DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.password(password);
        dataSourceBuilder.username(name);
        DataSource dataSource = dataSourceBuilder.build();
        return dataSource;
    }
    @Bean
    LogMessageService logMessageService() {
        return new MakeLogMessageService();
    }
}
