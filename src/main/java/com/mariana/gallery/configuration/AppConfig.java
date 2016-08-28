package com.mariana.gallery.configuration;

import com.sun.org.apache.xml.internal.utils.URI;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@ComponentScan("com.mariana.gallery")
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/*").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(BasicDataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("com.mariana.gallery");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException, URI.MalformedURIException {
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);

        if (dbUri.getUserinfo() != null) {
            String username = System.getenv("JDBC_DATABASE_USERNAME");
            String password = System.getenv("JDBC_DATABASE_PASSWORD");

            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
        }
        basicDataSource.setInitialSize(16);
        return basicDataSource;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);

        return resolver;
    }
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:validation");
        return source;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMEResolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("Exception", "error");

        simpleMEResolver.setExceptionMappings(mappings);

        simpleMEResolver.setDefaultErrorView("error");
        return simpleMEResolver;
    }
}
