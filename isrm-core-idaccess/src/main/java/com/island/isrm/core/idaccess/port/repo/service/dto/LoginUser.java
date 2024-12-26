package com.island.isrm.core.idaccess.port.repo.service.dto;

import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import lombok.Data;

/**
 * 登录用户信息传输对象
 * 用于在系统间传递登录用户的基本信息和令牌
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
public class LoginUser {
    /**
     * 用户登录令牌，用于验证用户身份
     */
    private String token;

    /**
     * 用户名，通常用于用户登录
     */
    private String userName;

    /**
     * 用户类型，描述用户的角色或权限级别
     */
    private String userType;

    /**
     * 用户昵称，用户友好的显示名称
     */
    private String nickname;

    /**
     * 用户供应商信息，描述用户来自哪个供应商或组织
     */
    private UserSupplier userSupplier;
}
