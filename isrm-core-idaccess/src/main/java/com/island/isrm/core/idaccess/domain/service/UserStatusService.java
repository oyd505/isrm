package com.island.isrm.core.idaccess.domain.service;

import com.island.isrm.core.idaccess.domain.dp.UserRole;
import com.island.isrm.core.idaccess.domain.dp.UserStatus;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户状态服务类，负责处理用户状态的相关业务逻辑
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class UserStatusService {

    /**
     * 根据用户类型初始化用户状态
     *
     * @param userType 用户类型，决定用户的初始角色和状态
     * @return 返回初始化后的用户状态对象
     */
    public UserStatus init(UserType userType) {
        if (userType.isAdmin()) {
            // 管理员类型用户同时具有买家和供应商角色
            return new UserStatus(String.join(",", UserRole.BUYER.name(), UserRole.VENDOR.name()), false, false);
        } else if (userType.isExternal()) {
            // 外部类型用户仅具有供应商角色
            return new UserStatus(UserRole.VENDOR.name(), false, false);
        } else {
            // 默认类型用户仅具有买家角色
            return new UserStatus(UserRole.BUYER.name(), false, false);
        }
    }

    /**
     * 更新用户的角色信息
     *
     * @param user  需要更新角色信息的用户对象
     * @param roles 用户的新角色列表
     */
    public void updateRoles(User user, List<String> roles) {
        // 创建新的用户状态对象，仅更新角色信息，其他状态保持不变
        UserStatus userStatus = new UserStatus(String.join(",", roles),
                user.getUserStatus().isDisabled(), user.getUserStatus().isAccountLocked());
        user.setUserStatus(userStatus);
    }

    /**
     * 更新用户的禁用状态
     *
     * @param user     需要更新禁用状态的用户对象
     * @param disabled 用户的新禁用状态
     */
    public void updateDisabled(User user, boolean disabled) {
        // 创建新的用户状态对象，仅更新禁用状态，其他信息保持不变
        UserStatus userStatus = new UserStatus(user.getUserStatus().getRoles()
                , disabled, user.getUserStatus().isAccountLocked());
        user.setUserStatus(userStatus);
    }

    /**
     * 更新用户的账号锁定状态
     *
     * @param user          需要更新账号锁定状态的用户对象
     * @param accountLocked 用户的新账号锁定状态
     */
    public void updateAccountLocked(User user, boolean accountLocked) {
        // 创建新的用户状态对象，仅更新账号锁定状态，其他信息保持不变
        UserStatus userStatus = new UserStatus(user.getUserStatus().getRoles(),
                user.getUserStatus().isDisabled(), accountLocked);
        user.setUserStatus(userStatus);
    }
}
