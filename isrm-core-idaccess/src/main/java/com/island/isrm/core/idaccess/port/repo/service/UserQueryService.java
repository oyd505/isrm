package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.UserRole;
import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQueryService {
    private List<UserDO> userCache = new ArrayList<>();

    public UserQueryService() {
        userCache.add(new UserDO().setUserCode("admin").setName("ADMIN")
                .setPassword("{bcrypt}$2a$10$/eGIaZEBVdHgu64T1rnh4eMADZgtm5bNP/7Lr5/CeyFD3eTTs3GX2")
                .setUserType(UserType.ADMIN.name()).setRoles(String.join(",", UserRole.INQUIRY.name(), UserRole.QUOTE.name()))
                .setSupplierCode("S1001").setSupplierName("鑫源供应商有限公司"));
        userCache.add(new UserDO().setUserCode("E1000").setName("北堂宇")
                .setPassword("{bcrypt}$2a$10$PafSj3FCUjLfqGvkzmTzyOR.tc6NFe8jo3AsV.3OSXR4RxonWBSdq")
                .setUserType(UserType.EMPLOYEE.name()).setRoles(UserRole.INQUIRY.name()));
        userCache.add(new UserDO().setUserCode("S1000").setName("华润供应商有限公司")
                .setPassword("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO")
                .setUserType(UserType.SUPPLIER.name()).setRoles(UserRole.QUOTE.name())
                .setSupplierCode("S1000").setSupplierName("华润供应商有限公司"));
        userCache.add(new UserDO().setUserCode("S1001").setName("鑫源供应商有限公司")
                .setPassword("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO")
                .setUserType(UserType.SUPPLIER.name()).setRoles(UserRole.QUOTE.name())
                .setSupplierCode("S1001").setSupplierName("鑫源供应商有限公司")
                .setDisabled(true));
    }

    public LoginUser findLoginUser(String userCode) {
        UserDO userDO = find(userCode);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserCode(userCode);
        loginUser.setUserName(userDO.getName());
        loginUser.setUserType(userDO.getUserType());
        if (UserType.valueOf(userDO.getUserType()).isExternal()) {
            loginUser.setUserSupplier(new UserSupplier(userDO.getSupplierCode(), userDO.getSupplierName()));
        }
        return loginUser;
    }

    public UserDO find(String userCode) {
        return userCache.stream().filter(user -> user.getUserCode().equals(userCode)).findFirst()
                .orElseThrow(() -> new DataNotFoundException(userCode + " 编号的用户不存在"));
    }
}
