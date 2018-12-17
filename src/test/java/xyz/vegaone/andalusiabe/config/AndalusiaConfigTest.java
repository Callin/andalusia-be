package xyz.vegaone.andalusiabe.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AndalusiaConfigTest {
    @Bean
    @LiquibaseDataSource
    public DataSource dataSource(DatasourceProperties datasourceProperties) {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(datasourceProperties.getDataSource().getUrl());
        hikariDataSource.setUsername(datasourceProperties.getDataSource().getUsername());
        hikariDataSource.setPassword(datasourceProperties.getDataSource().getPassword());

        return hikariDataSource;
    }
}
