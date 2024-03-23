package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.entity.CustomUser;
import com.island.isrm.core.idaccess.domain.repo.CustomUserRepository;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final CustomUserRepository customUserRepository;

    public UserQueryService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public LoginUser findLoginUser(String userCode) {
        CustomUser customUser = customUserRepository.find(new UserCode(userCode));
        LoginUser loginUser = new LoginUser();
        loginUser.setUserCode(userCode);
        loginUser.setUserName(customUser.getName().getValue());
        loginUser.setUserType(customUser.getUserType().name());
        loginUser.setSupplier(customUser.getSupplier());
        return loginUser;
    }
}
