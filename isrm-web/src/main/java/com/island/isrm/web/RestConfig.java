package com.island.isrm.web;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 配置类，用于设置REST接口的安全配置。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Configuration
@EnableMethodSecurity
public class RestConfig {

    /**
     * 从配置文件中注入公钥，用于JWT解码。
     */
    @Value("${jwt.public.key}")
    RSAPublicKey key;

    /**
     * 从配置文件中注入私钥，用于JWT编码。
     */
    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    /**
     * 配置HTTP安全过滤链。
     *
     * @param http HttpSecurity实例，用于配置安全设置。
     * @return 配置好的SecurityFilterChain。
     * @throws Exception 可能发生的异常。
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/product/list/code/name").permitAll() // 允许所有用户访问指定路径
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .cors((cors) -> cors.configure(http)) // 配置跨域资源共享
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/login")) // 忽略登录路径的CSRF检查
                .httpBasic(Customizer.withDefaults()) // 启用HTTP基本认证
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())) // 启用OAuth2资源服务器并使用JWT
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 设置无状态会话管理策略
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // 自定义认证入口点
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()) // 自定义访问拒绝处理器
                );
        return http.build();
    }

    /**
     * 配置JWT身份验证转换器。
     *
     * @return JwtAuthenticationConverter实例。
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // 移除权限前缀SCOPE_
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * 提供密码编码器。
     *
     * @return PasswordEncoder实例。
     */
    @Bean
    PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 配置JWT解码器。
     *
     * @return JwtDecoder实例。
     */
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    /**
     * 配置JWT编码器。
     *
     * @return JwtEncoder实例。
     */
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build(); // 构建RSA密钥
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk)); // 创建不可变的JWK集合作为JWK源
        return new NimbusJwtEncoder(jwks); // 返回NimbusJwtEncoder实例
    }

}
