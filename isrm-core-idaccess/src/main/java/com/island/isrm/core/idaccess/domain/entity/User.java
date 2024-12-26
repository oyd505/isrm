package com.island.isrm.core.idaccess.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.idaccess.domain.dp.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户实体类，继承自BaseEntity，用于表示系统中的用户信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class User extends BaseEntity {
    // 用户名，不可变属性，通过构造函数设置
    private final UserName userName;
    // 昵称，可变属性，通过setter方法设置
    @Setter
    private Name nickname;
    // 密码，可变属性，通过setter方法设置
    @Setter
    private Password password;
    // 用户类型，可变属性，通过setter方法设置
    @Setter
    private UserType userType;
    // 用户状态，可变属性，通过setter方法设置
    @Setter
    private UserStatus userStatus;
    // 用户供应商关联信息，可变属性，通过setter方法设置
    @Setter
    private UserSupplier userSupplier;
    // 用户员工关联信息，可变属性，通过setter方法设置
    @Setter
    private UserEmployee userEmployee;

    /**
     * 构造函数，创建一个具有指定用户名的用户实例
     *
     * @param userName 用户名，作为用户的唯一标识
     */
    public User(UserName userName) {
        this.userName = userName;
    }

    /**
     * 检查用户是否可以删除
     *
     * @throws BizException 如果用户未被禁用，则抛出此异常
     */
    public void checkRemoved() {
        if (!userStatus.isDisabled()) {
            throw new BizException("已启用用户不允许删除");
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 需要更新的用户信息
     */
    public void update(User user) {
        this.nickname = user.nickname;
        this.userType = user.userType;
        this.userSupplier = user.userSupplier;
        this.userEmployee = user.userEmployee;
    }

}
