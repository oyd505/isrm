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

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(timeFormatter));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(timeFormatter));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            builder.modules(javaTimeModule);
        };
    }
}
