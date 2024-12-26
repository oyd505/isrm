/**
 * ISRM系统启动类
 * 使用Spring Boot框架实现系统启动配置
 * 该类负责初始化应用程序，扫描指定包下的组件、配置JPA审计、启用事务管理等功能
 */
package com.island.isrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ISRM系统启动类
 * 使用Spring Boot框架实现系统启动配置
 * 该类负责初始化应用程序，扫描指定包下的组件、配置JPA审计、启用事务管理等功能
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.island.isrm.core", "com.island.isrm.web"})
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class IsrmWebApplication {

    /**
     * 系统启动入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(IsrmWebApplication.class, args);
    }

}
