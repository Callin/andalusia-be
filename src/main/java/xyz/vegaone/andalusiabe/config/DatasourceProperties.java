package xyz.vegaone.andalusiabe.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "spring")
public class DatasourceProperties {
    private DataSource dataSource = new DataSource();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Getter
    @Setter
    class DataSource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
