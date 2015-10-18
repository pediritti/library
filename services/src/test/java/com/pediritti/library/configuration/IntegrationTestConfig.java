package com.pediritti.library.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories()
@ComponentScan(basePackages = {
        "com.pediritti.library.service",
        "com.pediritti.library.business",
        "com.pediritti.library.util"
})
@EntityScan(basePackages = {"com.pediritti.library.domain"})
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class IntegrationTestConfig {
}
