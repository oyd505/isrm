package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.User;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(source = "userName.value", target = "userName")
    @Mapping(source = "nickname.value", target = "nickname")
    @Mapping(source = "password.value", target = "password")
    @Mapping(source = "userType", target = "userType")
    @Mapping(source = "userStatus.roles", target = "roles")
    @Mapping(source = "userStatus.disabled", target = "disabled")
    @Mapping(source = "userStatus.accountLocked", target = "accountLocked")
    @Mapping(source = "userSupplier.code", target = "supplierCode")
    @Mapping(source = "userSupplier.name", target = "supplierName")
    @Mapping(source = "userEmployee.code", target = "employeeCode")
    @Mapping(source = "userEmployee.name", target = "employeeName")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    UserDO fromEntity(User user);

    @Mapping(target = "userName.value", source = "userName")
    @Mapping(target = "nickname.value", source = "nickname")
    @Mapping(target = "password.value", source = "password")
    @Mapping(target = "userType", source = "userType")
    @Mapping(target = "userStatus.roles", source = "roles")
    @Mapping(target = "userStatus.disabled", source = "disabled")
    @Mapping(target = "userStatus.accountLocked", source = "accountLocked")
    @Mapping(target = "userSupplier.code", source = "supplierCode")
    @Mapping(target = "userSupplier.name", source = "supplierName")
    @Mapping(target = "userEmployee.code", source = "employeeCode")
    @Mapping(target = "userEmployee.name", source = "employeeName")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    User toEntity(UserDO userDO);
}
