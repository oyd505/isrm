package com.island.isrm.core.idaccess.application.impl;

import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.common.domain.event.EmployeeCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.idaccess.application.UserAppService;
import com.island.isrm.core.idaccess.application.UserAssembler;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdatePasswordCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.dp.UserStatus;
import com.island.isrm.core.idaccess.domain.entity.User;
import com.island.isrm.core.idaccess.domain.external.UserPasswordService;
import com.island.isrm.core.idaccess.domain.repo.UserRepository;
import com.island.isrm.core.idaccess.domain.service.UserStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户应用服务实现类，负责处理与用户相关的应用层逻辑
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Service
public class UserAppServiceImpl implements UserAppService {
    // 用户组装器，用于在领域对象和DTO之间进行转换
    private final UserAssembler userAssembler = UserAssembler.instance;
    // 用户仓储，负责用户数据的持久化
    private final UserRepository userRepository;
    // 用户密码服务，负责处理与用户密码相关的逻辑
    private final UserPasswordService userPasswordService;
    // 用户状态服务，负责处理与用户状态相关的逻辑
    private final UserStatusService userStatusService;

    /**
     * 构造函数，初始化用户应用服务实现类
     *
     * @param userRepository      用户仓储
     * @param userPasswordService 用户密码服务
     * @param userStatusService   用户状态服务
     */
    public UserAppServiceImpl(UserRepository userRepository, UserPasswordService userPasswordService, UserStatusService userStatusService) {
        this.userRepository = userRepository;
        this.userPasswordService = userPasswordService;
        this.userStatusService = userStatusService;
    }

    /**
     * 创建用户
     *
     * @param command 创建用户的命令，包含用户的相关信息
     * @return 创建的用户名
     */
    @Transactional
    @Override
    public UserName create(CreateUserCmd command) {
        User user = this.userAssembler.toAddEntity(command, new UserName(command.generateUserName()));
        return this.createHandler(user);
    }

    /**
     * 根据供应商事件创建用户
     *
     * @param event 供应商创建事件，包含供应商的相关信息
     */
    @Transactional
    @Override
    public void create(SupplierCreatedEvent event) {
        String supplierCode = event.getSupplierCode();
        String supplierName = event.getSupplierName();
        User user = this.userAssembler.toAddEntityForSupplier(supplierCode, supplierName);
        this.createHandler(user);
    }

    /**
     * 根据供应商联系人事件创建用户
     *
     * @param event 供应商联系人创建事件，包含供应商联系人的相关信息
     */
    @Override
    public void create(SupplierContactCreatedEvent event) {
        String supplierCode = event.getSupplierCode();
        String supplierName = event.getSupplierName();
        String phone = event.getPhone();
        String contactName = event.getContactName();
        User user = this.userAssembler.toAddEntityForSupplier(phone, contactName, supplierCode, supplierName);
        this.createHandler(user);
    }

    /**
     * 根据员工事件创建用户
     *
     * @param event 员工创建事件，包含员工的相关信息
     */
    @Override
    public void create(EmployeeCreatedEvent event) {
        String employeeCode = event.getEmployeeCode();
        String employeeName = event.getEmployeeName();
        User user = this.userAssembler.toAddEntityForEmployee(employeeCode, employeeName);
        this.createHandler(user);
    }

    /**
     * 创建用户的处理函数，负责设置用户密码和状态，并保存用户
     *
     * @param user 待创建的用户实体
     * @return 创建的用户名
     */
    private UserName createHandler(User user) {
        Password password = this.userPasswordService.generatePassword();
        user.setPassword(password);
        UserStatus userStatus = this.userStatusService.init(user.getUserType());
        user.setUserStatus(userStatus);
        return this.userRepository.add(user);
    }

    /**
     * 更新用户信息
     *
     * @param command 更新用户的命令，包含需要更新的用户信息
     */
    @Transactional
    @Override
    public void update(UpdateUserCmd command) {
        User update = this.userAssembler.toUpdateEntity(command);
        User user = this.userRepository.find(update.getUserName());
        user.update(update);
        this.userRepository.update(user);
    }

    /**
     * 更新用户密码
     *
     * @param userName 用户名
     * @param command  更新密码的命令，包含旧密码和新密码
     */
    @Transactional
    @Override
    public void updatePassword(UserName userName, UpdatePasswordCmd command) {
        User user = this.userRepository.find(userName);
        Password password = this.userPasswordService.updatePassword(command.getOldPassword(), command.getNewPassword(), user.getPassword());
        user.setPassword(password);
        this.userRepository.update(user);
    }

    /**
     * 更新用户角色
     *
     * @param userName 用户名
     * @param roles    用户的新角色列表
     */
    @Transactional
    @Override
    public void updateRoles(UserName userName, List<String> roles) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateRoles(user, roles);
        this.userRepository.update(user);
    }

    /**
     * 禁用用户
     *
     * @param userName 用户名
     */
    @Transactional
    @Override
    public void disable(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateDisabled(user, true);
        this.userRepository.update(user);
    }

    /**
     * 启用用户
     *
     * @param userName 用户名
     */
    @Transactional
    @Override
    public void enable(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateDisabled(user, false);
        this.userRepository.update(user);
    }

    /**
     * 锁定用户
     *
     * @param userName 用户名
     */
    @Transactional
    @Override
    public void lock(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateAccountLocked(user, true);
        this.userRepository.update(user);
    }

    /**
     * 解锁用户
     *
     * @param userName 用户名
     */
    @Transactional
    @Override
    public void unlock(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateAccountLocked(user, false);
        this.userRepository.update(user);
    }

    /**
     * 删除用户
     *
     * @param userName 用户名
     */
    @Transactional
    @Override
    public void remove(UserName userName) {
        User user = this.userRepository.find(userName);
        user.checkRemoved();
        this.userRepository.remove(userName);
    }
}
