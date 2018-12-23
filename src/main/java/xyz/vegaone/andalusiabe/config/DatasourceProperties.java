package xyz.vegaone.andalusiabe.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "spring")
public class DatasourceProperties {
    private DataSource dataSource = new DataSource();

    @Getter
    @Setter
    class DataSource {
        private String url;
        private String username;
        private String password;
    }
}
