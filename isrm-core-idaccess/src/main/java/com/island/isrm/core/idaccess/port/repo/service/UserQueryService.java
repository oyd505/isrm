package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.domain.dp.UserRole;
import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.port.repo.dao.UserJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQueryService {
    private List<UserDO> userCache = new ArrayList<>();
    private final UserJpa userJpa;

    public UserQueryService(UserJpa userJpa) {
        this.userJpa = userJpa;
        userCache.add(new UserDO().setUserName("admin").setNickname("ADMIN")
                .setPassword("{bcrypt}$2a$10$/eGIaZEBVdHgu64T1rnh4eMADZgtm5bNP/7Lr5/CeyFD3eTTs3GX2")
                .setUserType(UserType.ADMIN.name()).setRoles(String.join(",", UserRole.BUYER.name(), UserRole.VENDOR.name()))
                .setSupplierCode("S1001").setSupplierName("鑫源供应商有限公司"));
        userCache.add(new UserDO().setUserName("E1000").setNickname("北堂宇")
                .setPassword("{bcrypt}$2a$10$PafSj3FCUjLfqGvkzmTzyOR.tc6NFe8jo3AsV.3OSXR4RxonWBSdq")
                .setUserType(UserType.EMPLOYEE.name()).setRoles(UserRole.BUYER.name()));
        userCache.add(new UserDO().setUserName("S1000").setNickname("华润供应商有限公司")
                .setPassword("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO")
                .setUserType(UserType.SUPPLIER.name()).setRoles(UserRole.VENDOR.name())
                .setSupplierCode("S1000").setSupplierName("华润供应商有限公司"));
        userCache.add(new UserDO().setUserName("S1001").setNickname("鑫源供应商有限公司")
                .setPassword("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO")
                .setUserType(UserType.SUPPLIER.name()).setRoles(UserRole.VENDOR.name())
                .setSupplierCode("S1001").setSupplierName("鑫源供应商有限公司")
                .setDisabled(true));
    }


    public UserDO find(String userName) {
        return userCache.stream().filter(user -> user.getUserName().equals(userName)).findFirst()
                .orElse(new UserDO());
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
