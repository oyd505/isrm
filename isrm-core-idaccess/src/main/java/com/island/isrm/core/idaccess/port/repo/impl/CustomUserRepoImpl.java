package com.island.isrm.core.idaccess.port.repo.impl;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.Supplier;
import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.domain.entity.CustomUser;
import com.island.isrm.core.idaccess.domain.repo.CustomUserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomUserRepoImpl implements CustomUserRepository {
    private final Map<UserCode, CustomUser> userMap = new HashMap<>();

    public CustomUserRepoImpl() {
        CustomUser a1 = new CustomUser(new UserCode("admin"), new Name("ADMIN"),
                new Password("{bcrypt}$2a$10$/eGIaZEBVdHgu64T1rnh4eMADZgtm5bNP/7Lr5/CeyFD3eTTs3GX2"), UserType.ADMIN);
        userMap.put(a1.getUserCode(), a1);
        CustomUser e1 = new CustomUser(new UserCode("E1000"), new Name("北堂宇"),
                new Password("{bcrypt}$2a$10$PafSj3FCUjLfqGvkzmTzyOR.tc6NFe8jo3AsV.3OSXR4RxonWBSdq"), UserType.EMPLOYEE);
        userMap.put(e1.getUserCode(), e1);
        CustomUser s1 = new CustomUser(new UserCode("S1000"), new Name("华润供应商有限公司"),
                new Password("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO"), UserType.SUPPLIER);
        s1.setSupplier(new Supplier("S1000", "华润供应商有限公司"));
        userMap.put(s1.getUserCode(), s1);
        CustomUser s2 = new CustomUser(new UserCode("S1001"), new Name("鑫源供应商有限公司"),
                new Password("{bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO"), UserType.SUPPLIER);
        s2.setSupplier(new Supplier("S1001", "鑫源供应商有限公司"));
        userMap.put(s2.getUserCode(), s2);
    }

    @Override
    public CustomUser find(UserCode userCode) {
        return Optional.ofNullable(userMap.get(userCode)).orElseThrow(() -> new DataNotFoundException(userCode.getValue() + " 编号的用户不存在"));
    }
}
