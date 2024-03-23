package com.island.isrm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
         * 全局配直需要自定义类实现 WebMvcConfigurer 接口，然后实现接口中的 addCorsMappings方法。
         *
         *  在addCorsMappings 方法中， addMapping表示对哪种格式的请求路径进行跨域处理；
         *  allowedHeaders 表示允许的请求头，默认允许所有的请求头信息；
         *  allowedMethods 表示允许的请求方法，默认是 GET POST HEAD; ＊表示支持所有的请求方法；
         *  max.Age 表示探测请求的有效期；
         *  allowedOrigins表示支持的域
         * */
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("http://localhost:5173");
    }
}
