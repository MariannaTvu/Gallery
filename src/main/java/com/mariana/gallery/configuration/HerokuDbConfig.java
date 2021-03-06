package com.mariana.gallery.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.xml.internal.utils.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URISyntaxException;

/**
 * Created by Maryana on 28.10.2016.
 */
@Profile("heroku")
@Configuration
public class HerokuDbConfig {
    @Bean
    public DataSource dataSource() throws URISyntaxException, URI.MalformedURIException {

        String dbUrl = "jdbc:postgresql://ec2-54-228-235-185.eu-west-1.compute.amazonaws.com:5432/d9jhd09v6nov9j?sslmode=require";
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(dbUrl);
        cpds.setUser("nutavdhxrgcffl");
        cpds.setPassword("272PMO_33XXIyHQlMOoH-EXmIc");
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
