package com.island.isrm.core.idaccess.domain.dp;

import lombok.Data;

/**
 * 用户状态类，用于封装用户的角色和账户状态信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class UserStatus {
    /**
     * 用户角色，用于标识用户的权限级别
     */
    private final String roles;

    /**
     * 账户禁用状态，true表示账户已被禁用，false表示账户处于活动状态
     */
    private final boolean disabled;

    /**
     * 账户锁定状态，true表示账户已被锁定，false表示账户未被锁定
     * 锁定的账户无法进行操作
     */
    private final boolean accountLocked;

}
