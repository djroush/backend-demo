package com.github.djroush.demo.backend.dao.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class EmbeddedDataSourceConfig {
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2).setName("test")
           .addScripts(
            "/sql/schema.sql", 
            "/sql/tables/doctor.sql",
            "/sql/tables/appointment.sql"
            
        );
        return edb.build();
    }
    
    // This bean exposes the database so you can connect to it through a browser
    @Bean(name="h2WebServer",initMethod="start", destroyMethod="stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }
}
