package com.island.isrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.island.isrm.core", "com.island.isrm.web"})
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class IsrmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsrmWebApplication.class, args);
    }

}
