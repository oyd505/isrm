package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.event.EmployeeCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdatePasswordCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 用户应用服务接口，定义了用户相关的应用层操作，包括创建、更新、禁用、启用、锁定、解锁和删除用户等功能。
 * 该接口主要用来处理与用户相关的业务逻辑，确保用户数据的准确性和安全性。
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Validated
public interface UserAppService {

    /**
     * 创建用户方法，根据传入的创建用户命令创建新用户。
     *
     * @param command 创建用户命令，包含创建用户所需的信息。
     * @return 新创建用户的用户名。
     */
    UserName create(@Valid CreateUserCmd command);

    /**
     * 根据供应商创建事件创建用户。
     *
     * @param event 供应商创建事件，包含创建用户所需的信息。
     */
    void create(SupplierCreatedEvent event);

    /**
     * 根据供应商联系人创建事件创建用户。
     *
     * @param event 供应商联系人创建事件，包含创建用户所需的信息。
     */
    void create(SupplierContactCreatedEvent event);

    /**
     * 根据员工创建事件创建用户。
     *
     * @param event 员工创建事件，包含创建用户所需的信息。
     */
    void create(EmployeeCreatedEvent event);

    /**
     * 更新用户信息方法，根据传入的更新用户命令修改用户信息。
     *
     * @param command 更新用户命令，包含需要更新的用户信息。
     */
    void update(@Valid UpdateUserCmd command);

    /**
     * 更新用户密码方法，根据传入的更新密码命令修改用户密码。
     *
     * @param userName 用户名，标识需要更新密码的用户。
     * @param command  更新密码命令，包含新的密码信息。
     */
    void updatePassword(UserName userName, @Valid UpdatePasswordCmd command);

    /**
     * 更新用户角色方法，根据传入的角色列表更新用户的角色。
     *
     * @param userName 用户名，标识需要更新角色的用户。
     * @param roles    角色列表，包含用户新的角色。
     */
    void updateRoles(UserName userName, List<String> roles);

    /**
     * 禁用用户方法，将用户状态设置为禁用。
     *
     * @param userName 用户名，标识需要禁用的用户。
     */
    void disable(UserName userName);

    /**
     * 启用用户方法，将用户状态设置为启用。
     *
     * @param userName 用户名，标识需要启用的用户。
     */
    void enable(UserName userName);

    /**
     * 锁定用户方法，将用户状态设置为锁定。
     *
     * @param userName 用户名，标识需要锁定的用户。
     */
    void lock(UserName userName);

    /**
     * 解锁用户方法，将用户状态设置为解锁。
     *
     * @param userName 用户名，标识需要解锁的用户。
     */
    void unlock(UserName userName);

    /**
     * 删除用户方法，根据用户名删除用户。
     *
     * @param userName 用户名，标识需要删除的用户。
     */
    void remove(UserName userName);
}
