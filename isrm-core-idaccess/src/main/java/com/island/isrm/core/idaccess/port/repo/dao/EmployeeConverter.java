package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.Employee;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * EmployeeConverter接口用于在Employee实体和EmployeeDO数据对象之间进行转换。
 * 通过MapStruct框架自动生成转换逻辑，确保编码的简洁性和可维护性。
 * <p>
 * 注意：MapStruct会根据接口定义自动生成实现类，因此无需手动编写转换逻辑。
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Mapper(componentModel = "Spring")
public interface EmployeeConverter {
    // MapStruct自动生成的EmployeeConverter实例，便于在Spring框架中使用。
    EmployeeConverter INSTANCE = Mappers.getMapper(EmployeeConverter.class);

    /**
     * 将Employee实体转换为EmployeeDO数据对象。
     *
     * @param employee 要转换的Employee实体。
     * @return 转换后的EmployeeDO数据对象。
     */
    @Mapping(source = "employeeCode.value", target = "employeeCode")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "department.code", target = "departmentCode")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "jobTitle.value", target = "jobTitle")
    @Mapping(source = "employeeContact.phone", target = "phone")
    @Mapping(source = "employeeContact.email", target = "email")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    EmployeeDO fromEntity(Employee employee);

    /**
     * 将EmployeeDO数据对象转换为Employee实体。
     *
     * @param employeeDO 要转换的EmployeeDO数据对象。
     * @return 转换后的Employee实体。
     */
    @Mapping(target = "employeeCode.value", source = "employeeCode")
    @Mapping(target = "name.value", source = "name")
    @Mapping(target = "department.code", source = "departmentCode")
    @Mapping(target = "department.name", source = "departmentName")
    @Mapping(target = "jobTitle.value", source = "jobTitle")
    @Mapping(target = "employeeContact.phone", source = "phone")
    @Mapping(target = "employeeContact.email", source = "email")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    Employee toEntity(EmployeeDO employeeDO);
}
