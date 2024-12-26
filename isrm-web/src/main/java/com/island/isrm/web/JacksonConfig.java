package com.island.isrm.web;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson配置类，用于定制日期和时间的序列化和反序列化方式
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Configuration
public class JacksonConfig {

    /**
     * 创建Jackson2ObjectMapperBuilderCustomizer的Bean，用于配置Jackson的序列化和反序列化行为
     *
     * @return Jackson2ObjectMapperBuilderCustomizer对象，用于定制Jackson的配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            // 启用将日期作为时间戳写入的特性
            builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 创建JavaTimeModule对象，用于支持Java 8时间模块的序列化和反序列化
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            // 定义时间格式化器，用于LocalDateTime的序列化和反序列化
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // 定义日期格式化器，用于LocalDate的序列化和反序列化
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // 为LocalDateTime添加自定义的序列化和反序列化器
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(timeFormatter));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(timeFormatter));
            // 为LocalDate添加自定义的序列化和反序列化器
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            // 将JavaTimeModule添加到builder中，以便应用上述配置
            builder.modules(javaTimeModule);
        };
    }
}

