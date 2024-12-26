package com.island.isrm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类用于全局跨域处理
 * 跨域资源共享（CORS）配置是现代Web服务中常见需求，特别是在单页应用（SPA）与后端服务交互时
 * 通过实现WebMvcConfigurer接口，自定义Spring Boot的Web配置
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 添加全局跨域映射配置
     *
     * @param registry CorsRegistry对象，用于注册跨域配置
     *                 <p>
     *                 全局配置需要自定义类实现 WebMvcConfigurer 接口，然后实现接口中的 addCorsMappings方法。
     *                 在addCorsMappings 方法中， addMapping表示对哪种格式的请求路径进行跨域处理；
     *                 allowedHeaders 表示允许的请求头，默认允许所有的请求头信息；
     *                 allowedMethods 表示允许的请求方法，默认是 GET POST HEAD; ＊表示支持所有的请求方法；
     *                 maxAge 表示探测请求的有效期；
     *                 allowedOrigins表示支持的域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("http://localhost:5173");
    }
}
