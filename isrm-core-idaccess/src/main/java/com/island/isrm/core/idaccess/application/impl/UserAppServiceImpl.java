package com.island.isrm.core.idaccess.application.impl;

import com.island.isrm.core.common.domain.dp.Password;
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

@Service
public class UserAppServiceImpl implements UserAppService {
    private final UserAssembler userAssembler = UserAssembler.instance;
    private final UserRepository userRepository;
    private final UserPasswordService userPasswordService;
    private final UserStatusService userStatusService;

    public UserAppServiceImpl(UserRepository userRepository, UserPasswordService userPasswordService, UserStatusService userStatusService) {
        this.userRepository = userRepository;
        this.userPasswordService = userPasswordService;
        this.userStatusService = userStatusService;
    }

    @Transactional
    @Override
    public UserName create(CreateUserCmd command) {
        User user = this.userAssembler.toAddEntity(command, new UserName(command.generateUserName()));
        return this.createHandler(user);
    }

    @Transactional
    @Override
    public void create(SupplierCreatedEvent event) {
        String supplierCode = event.getSupplierCode();
        String supplierName = event.getSupplierName();
        User user = this.userAssembler.toAddEntity(supplierCode, supplierName);
        this.createHandler(user);
    }

    @Override
    public void create(SupplierContactCreatedEvent event) {
        String supplierCode = event.getSupplierCode();
        String supplierName = event.getSupplierName();
        String phone = event.getPhone();
        String contactName = event.getContactName();
        User user = this.userAssembler.toAddEntity(phone, contactName, supplierCode, supplierName);
        this.createHandler(user);
    }

    private UserName createHandler(User user) {
        Password password = this.userPasswordService.generatePassword();
        user.setPassword(password);
        UserStatus userStatus = this.userStatusService.init(user.getUserType());
        user.setUserStatus(userStatus);
        return this.userRepository.add(user);
    }

    @Transactional
    @Override
    public void update(UpdateUserCmd command) {
        User update = this.userAssembler.toUpdateEntity(command);
        User user = this.userRepository.find(update.getUserName());
        user.update(update);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void updatePassword(UserName userName, UpdatePasswordCmd command) {
        User user = this.userRepository.find(userName);
        Password password = this.userPasswordService.updatePassword(command.getOldPassword(), command.getNewPassword(), user.getPassword());
        user.setPassword(password);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void updateRoles(UserName userName, List<String> roles) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateRoles(user, roles);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void disable(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateDisabled(user, true);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void enable(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateDisabled(user, false);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void lock(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateAccountLocked(user, true);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void unlock(UserName userName) {
        User user = this.userRepository.find(userName);
        this.userStatusService.updateAccountLocked(user, false);
        this.userRepository.update(user);
    }

    @Transactional
    @Override
    public void remove(UserName userName) {
        User user = this.userRepository.find(userName);
        user.checkRemoved();
        this.userRepository.remove(userName);
    }
}
