package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.port.repo.dao.UserJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UserJpa userJpa;

    public UserQueryService(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    public UserDO find(String userName) {
        return userJpa.findById(userName).orElse(new UserDO());
    }

    public Page<UserBasic> pageBasic(Pageable pageable) {
        return userJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    public LoginUser findLoginUser(String userName) {
        UserDO userDO = find(userName);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(userName);
        loginUser.setNickname(userDO.getNickname());
        loginUser.setUserType(userDO.getUserType());
        if (UserType.valueOf(userDO.getUserType()).isExternal()) {
            loginUser.setUserSupplier(new UserSupplier(userDO.getSupplierCode(), userDO.getSupplierName()));
        }
        return loginUser;
    }
}
