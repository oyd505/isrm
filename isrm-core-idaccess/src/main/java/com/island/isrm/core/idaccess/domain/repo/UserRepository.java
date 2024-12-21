package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.entity.User;

public interface UserRepository {
    User find(UserName userName);

    UserName add(User user);

    void update(User user);

    void remove(UserName userName);
}
