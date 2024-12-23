package com.island.isrm.core.idaccess.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.entity.Employee;
import com.island.isrm.core.idaccess.domain.repo.EmployeeRepository;
import com.island.isrm.core.idaccess.port.repo.dao.EmployeeConverter;
import com.island.isrm.core.idaccess.port.repo.dao.EmployeeJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJpa employeeJpa;
    private final EmployeeConverter employeeConverter;

    public EmployeeRepositoryImpl(EmployeeJpa employeeJpa, EmployeeConverter employeeConverter) {
        this.employeeJpa = employeeJpa;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public Employee find(EmployeeCode employeeCode) {
        Optional<EmployeeDO> employeeDO = employeeJpa.findById(employeeCode.getValue());
        return employeeConverter.toEntity(employeeDO.orElseThrow(
                () -> new DataNotFoundException(String.format("员工(%s)不存在", employeeCode.getValue()))
        ));
    }

    @Override
    public EmployeeCode add(Employee employee) {
        EmployeeDO employeeDO = employeeConverter.fromEntity(employee);
        return new EmployeeCode(employeeJpa.save(employeeDO).getEmployeeCode());
    }

    @Override
    public void update(Employee employee) {
        EmployeeDO employeeDO = employeeConverter.fromEntity(employee);
        employeeJpa.save(employeeDO);
    }

    @Override
    public void remove(EmployeeCode employeeCode) {
        employeeJpa.deleteById(employeeCode.getValue());
    }
}
