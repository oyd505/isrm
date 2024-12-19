package com.island.isrm.core.idaccess.port.repo.impl;

import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.entity.CustomUser;
import com.island.isrm.core.idaccess.domain.repo.CustomUserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomUserRepoImpl implements CustomUserRepository {
    private final Map<UserCode, CustomUser> userMap = new HashMap<>();

    @Override
    public CustomUser find(UserCode userCode) {
        return null;
    }
}
