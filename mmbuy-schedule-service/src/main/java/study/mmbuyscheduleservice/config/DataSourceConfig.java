package study.mmbuyscheduleservice.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean("stockDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.stock")
    public DataSource stockDataSource(){
        return DataSourceBuilder.create().build();
    }
}
