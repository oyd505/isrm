package com.island.isrm.web.service;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.service.UserQueryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 自定义用户详细信息服务类，实现Spring Security的UserDetailsService接口
 * 用于加载用户信息，是Spring Security配置中不可或缺的一部分
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    // 用户查询服务，用于从数据源获取用户信息
    private final UserQueryService userQueryService;

    /**
     * 构造函数注入UserQueryService
     *
     * @param userQueryService 用户查询服务
     */
    public CustomUserDetailsService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * 根据用户名加载用户详细信息
     * 此方法是UserDetailsService接口的核心，用于根据用户名获取用户信息
     * 如果找不到用户，则抛出UsernameNotFoundException异常
     *
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果找不到用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询用户信息
        UserDO userDO = userQueryService.find(username);
        // 检查用户是否存在，如果不存在则抛出异常
        if (!StringUtils.hasText(userDO.getUserName())) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        // 构建并返回用户详细信息
        return User.withUsername(userDO.getUserName()).password(userDO.getPassword()).roles(userDO.getRoles().split(","))
                .disabled(userDO.isDisabled()).accountLocked(userDO.isAccountLocked()).build();
    }
}
