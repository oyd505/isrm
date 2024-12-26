package com.island.isrm.core.idaccess.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.entity.User;
import com.island.isrm.core.idaccess.domain.repo.UserRepository;
import com.island.isrm.core.idaccess.port.repo.dao.UserConverter;
import com.island.isrm.core.idaccess.port.repo.dao.UserJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 用户仓储实现类，提供用户数据访问的实现
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Component
public class UserRepoImpl implements UserRepository {
    private final UserJpa userJpa;
    private final UserConverter userConverter;

    public UserRepoImpl(UserJpa userJpa, UserConverter userConverter) {
        this.userJpa = userJpa;
        this.userConverter = userConverter;
    }

    /**
     * 根据用户名查找用户实体
     *
     * @param userName 用户名
     * @return 用户实体
     * @throws DataNotFoundException 如果用户不存在，则抛出数据未找到异常
     */
    @Override
    public User find(UserName userName) {
        Optional<UserDO> userDO = this.userJpa.findById(userName.getValue());
        return this.userConverter.toEntity(userDO.orElseThrow(
                () -> new DataNotFoundException(String.format("用户(%s)不存在", userName.getValue()))
        ));
    }

    /**
     * 添加新用户
     *
     * @param user 用户实体
     * @return 添加后的用户名
     */
    @Override
    public UserName add(User user) {
        UserDO userDO = this.userConverter.fromEntity(user);
        return new UserName(this.userJpa.save(userDO).getUserName());
    }

    /**
     * 更新用户信息
     *
     * @param user 需要更新的用户实体
     */
    @Override
    public void update(User user) {
        UserDO userDO = this.userConverter.fromEntity(user);
        this.userJpa.save(userDO);
    }

    /**
     * 删除用户
     *
     * @param userName 需要删除的用户名
     */
    @Override
    public void remove(UserName userName) {
        this.userJpa.deleteById(userName.getValue());
    }
}
