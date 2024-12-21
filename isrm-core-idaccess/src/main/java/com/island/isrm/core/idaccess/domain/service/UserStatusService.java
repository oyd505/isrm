package com.island.isrm.core.idaccess.domain.service;

import com.island.isrm.core.idaccess.domain.dp.UserRole;
import com.island.isrm.core.idaccess.domain.dp.UserStatus;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusService {

    public UserStatus init(UserType userType) {
        if (userType.isAdmin()) {
            return new UserStatus(String.join(",", UserRole.BUYER.name(), UserRole.VENDOR.name()), false, false);
        } else if (userType.isExternal()) {
            return new UserStatus(UserRole.VENDOR.name(), false, false);
        } else {
            return new UserStatus(UserRole.BUYER.name(), false, false);
        }
    }

    public void updateRoles(User user, List<String> roles) {
        UserStatus userStatus = new UserStatus(String.join(",", roles),
                user.getUserStatus().isDisabled(), user.getUserStatus().isAccountLocked());
        user.setUserStatus(userStatus);
    }

    public void updateDisabled(User user, boolean disabled) {
        UserStatus userStatus = new UserStatus(user.getUserStatus().getRoles()
                , disabled, user.getUserStatus().isAccountLocked());
        user.setUserStatus(userStatus);
    }

    public void updateAccountLocked(User user, boolean accountLocked) {
        UserStatus userStatus = new UserStatus(user.getUserStatus().getRoles(),
                user.getUserStatus().isDisabled(), accountLocked);
        user.setUserStatus(userStatus);
    }
}
