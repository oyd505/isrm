/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.island.isrm.web.api;

import com.island.isrm.core.idaccess.port.repo.service.UserQueryService;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Token控制器类，负责处理与登录认证相关的请求
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@RestController
public class TokenController {
    private final UserQueryService userQueryService;

    @Autowired
    JwtEncoder encoder;

    /**
     * 构造方法注入UserQueryService
     *
     * @param userQueryService 用户查询服务
     */
    public TokenController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * 生成并返回登录用户信息及JWT令牌
     *
     * @param authentication 用户认证信息
     * @return 包含用户信息和JWT令牌的LoginUser对象
     */
    @PostMapping("/login")
    public LoginUser token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        // 构建JWT的claims，包括发行者、发行时间、过期时间、主题和权限范围
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        // 使用JwtEncoder编码生成JWT令牌
        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // 查询登录用户信息
        LoginUser loginUser = this.userQueryService.findLoginUser(authentication.getName());
        loginUser.setToken(token);
        return loginUser;
    }

}
