package com.github.djroush.demo.backend.dao.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {
    @Bean 
    public JdbcTemplate jdbcTemplate(DataSource h2DataSource) {
        return new JdbcTemplate(h2DataSource);
    }
}
