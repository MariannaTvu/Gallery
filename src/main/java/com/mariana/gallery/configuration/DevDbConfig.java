package com.mariana.gallery.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Maryana on 28.10.2016.
 */
@Profile("dev")
@Configuration
@PropertySource("classpath:db.properties")
public class DevDbConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource(@Value("${db.driver}") String dbDriver,
                                 @Value("${db.url}") String dbUrl,
                                 @Value("${db.user}") String dbUser,
                                 @Value("${db.password}") String dbPassword) {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(dbDriver);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        cpds.setJdbcUrl(dbUrl);
        cpds.setUser(dbUser);
        cpds.setPassword(dbPassword);


        cpds.setInitialPoolSize(3);
        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(2000);
        cpds.setTestConnectionOnCheckin(true);
        cpds.setTestConnectionOnCheckout(true);

        return cpds;
    }
}
