package fr.softeam.gestionevenement.config;

import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan("fr.softeam.gestionevenement")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver:org.postgresql.Driver}") final String driverClassName,
                                 @Value("${jdbc.url:jdbc:postgresql://localhost/postgres}") final String url,
                                 @Value("${jdbc.username:postgres}") final String username,
                                 @Value("${jdbc.password:postgres}") final String password) throws SQLException{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
