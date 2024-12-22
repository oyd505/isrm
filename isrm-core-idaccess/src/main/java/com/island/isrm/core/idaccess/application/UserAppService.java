package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdatePasswordCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserAppService {
    UserName create(@Valid CreateUserCmd command);

    void create(SupplierCreatedEvent event);

    void create(SupplierContactCreatedEvent event);

    void update(@Valid UpdateUserCmd command);

    void updatePassword(UserName userName, @Valid UpdatePasswordCmd command);

    void updateRoles(UserName userName, List<String> roles);

    void disable(UserName userName);

    void enable(UserName userName);

    void lock(UserName userName);

    void unlock(UserName userName);

    void remove(UserName userName);
}
