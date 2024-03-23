package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.entity.CustomUser;

public interface CustomUserRepository {
    CustomUser find(UserCode userCode);
}
