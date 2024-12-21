package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserEmployee;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.domain.entity.User;

public class UserAssembler {
    public final static UserAssembler instance = new UserAssembler();

    public User toAddEntity(CreateUserCmd command, UserName userName) {
        User user = new User(userName);
        this.assemble(user, command);
        return user;
    }

    private void assemble(User user, CreateUserCmd command) {
        user.setNickname(new Name(command.getNickname()));
        user.setUserType(UserType.valueOf(command.getUserType()));
        user.setUserEmployee(new UserEmployee(command.getEmployeeCode(), command.getEmployeeName()));
        user.setUserSupplier(new UserSupplier(command.getSupplierCode(), command.getSupplierName()));
    }

    public User toUpdateEntity(UpdateUserCmd command) {
        User update = new User(new UserName(command.getUserName()));
        this.assemble(update, command);
        return update;
    }
}
