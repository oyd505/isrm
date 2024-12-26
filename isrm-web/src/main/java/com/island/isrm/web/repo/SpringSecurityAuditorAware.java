package com.island.isrm.web.repo;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 实现AuditorAware接口，用于获取当前审计员的信息
 * 在Spring Data JPA中，通常用于记录创建或修改实体的用户信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    /**
     * 获取当前登录系统的用户信息
     * 该方法通过Spring Security框架提供的SecurityContextHolder获取当前安全上下文，
     * 进而提取出认证信息，确保用户已通过身份验证，并返回用户的名称
     *
     * @return Optional<String> 包含当前审计员名称的Optional对象，如果无法确定当前审计员，则返回空Optional
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // 获取当前安全上下文，并进行一系列的映射和过滤操作
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName);
    }
}
