package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.Employee;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface EmployeeConverter {
    EmployeeConverter INSTANCE = Mappers.getMapper(EmployeeConverter.class);

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
