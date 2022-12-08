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
    @Bean
    LogMessageService logMessageService() {
        return new MakeLogMessageService();
    }
}
