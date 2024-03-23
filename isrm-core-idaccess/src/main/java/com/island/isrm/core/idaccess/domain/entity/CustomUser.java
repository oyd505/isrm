package com.island.isrm.core.idaccess.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.idaccess.domain.dp.Supplier;
import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class CustomUser extends BaseEntity {
    private final UserCode userCode;
    private final Name name;
    private final Password password;
    private final UserType userType;
    @Setter
    private Supplier supplier;

    public CustomUser(UserCode userCode, Name name, Password password, UserType userType) {
        this.userCode = userCode;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }
}
