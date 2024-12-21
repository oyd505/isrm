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
 * 用户
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class User extends BaseEntity {
    private final UserName userName;
    @Setter
    private Name nickname;
    @Setter
    private Password password;
    @Setter
    private UserType userType;
    @Setter
    private UserStatus userStatus;
    @Setter
    private UserSupplier userSupplier;
    @Setter
    private UserEmployee userEmployee;

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

    public void update(User user) {
        this.nickname = user.nickname;
        this.userType = user.userType;
        this.userSupplier = user.userSupplier;
        this.userEmployee = user.userEmployee;
    }

}
