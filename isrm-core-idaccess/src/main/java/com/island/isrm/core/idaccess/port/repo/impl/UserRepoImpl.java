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

@Component
public class UserRepoImpl implements UserRepository {
    private final UserJpa userJpa;
    private final UserConverter userConverter;

    public UserRepoImpl(UserJpa userJpa, UserConverter userConverter) {
        this.userJpa = userJpa;
        this.userConverter = userConverter;
    }

    @Override
    public User find(UserName userName) {
        Optional<UserDO> userDO = this.userJpa.findById(userName.getValue());
        return this.userConverter.toEntity(userDO.orElseThrow(
                () -> new DataNotFoundException(String.format("用户(%s)不存在", userName.getValue()))
        ));
    }

    @Override
    public UserName add(User user) {
        UserDO userDO = this.userConverter.fromEntity(user);
        return new UserName(this.userJpa.save(userDO).getUserName());
    }

    @Override
    public void update(User user) {
        UserDO userDO = this.userConverter.fromEntity(user);
        this.userJpa.save(userDO);
    }

    @Override
    public void remove(UserName userName) {
        this.userJpa.deleteById(userName.getValue());
    }
}
