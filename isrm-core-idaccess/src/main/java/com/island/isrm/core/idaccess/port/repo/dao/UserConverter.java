package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.User;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体与数据对象相互转换的映射器接口
 * <p>
 * 该接口使用MapStruct框架来实现User实体类与UserDO数据传输对象之间的相互转换
 * MapStruct会自动生成实现类，简化了映射逻辑的编写
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Mapper(componentModel = "Spring")
public interface UserConverter {
    // MapStruct自动生成的映射器实例，便于在其他地方使用
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 将User实体转换为UserDO数据对象
     *
     * @param user User实体，包含用户相关信息
     * @return 转换后的UserDO对象
     */
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

    /**
     * 将UserDO数据对象转换为User实体
     *
     * @param userDO UserDO对象，包含用户相关信息
     * @return 转换后的User实体
     */
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
