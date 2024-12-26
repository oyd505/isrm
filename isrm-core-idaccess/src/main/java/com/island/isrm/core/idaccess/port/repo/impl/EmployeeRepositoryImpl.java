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

/**
 * 员工仓储实现类，提供员工数据访问的实现
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    // 员工JPA仓储，用于数据库操作
    private final EmployeeJpa employeeJpa;
    // 员工转换器，用于实体和数据对象之间的转换
    private final EmployeeConverter employeeConverter;

    /**
     * 构造函数注入EmployeeJpa和EmployeeConverter
     *
     * @param employeeJpa       员工JPA仓储
     * @param employeeConverter 员工转换器
     */
    public EmployeeRepositoryImpl(EmployeeJpa employeeJpa, EmployeeConverter employeeConverter) {
        this.employeeJpa = employeeJpa;
        this.employeeConverter = employeeConverter;
    }

    /**
     * 根据员工编码查找员工实体
     *
     * @param employeeCode 员工编码
     * @return 员工实体
     * @throws DataNotFoundException 如果找不到员工，则抛出数据不存在异常
     */
    @Override
    public Employee find(EmployeeCode employeeCode) {
        Optional<EmployeeDO> employeeDO = employeeJpa.findById(employeeCode.getValue());
        return employeeConverter.toEntity(employeeDO.orElseThrow(
                () -> new DataNotFoundException(String.format("员工(%s)不存在", employeeCode.getValue()))
        ));
    }

    /**
     * 添加新员工到数据库
     *
     * @param employee 新员工实体
     * @return 添加后的员工编码
     */
    @Override
    public EmployeeCode add(Employee employee) {
        EmployeeDO employeeDO = employeeConverter.fromEntity(employee);
        return new EmployeeCode(employeeJpa.save(employeeDO).getEmployeeCode());
    }

    /**
     * 更新员工信息
     *
     * @param employee 需要更新的员工实体
     */
    @Override
    public void update(Employee employee) {
        EmployeeDO employeeDO = employeeConverter.fromEntity(employee);
        employeeJpa.save(employeeDO);
    }

    /**
     * 根据员工编码删除员工
     *
     * @param employeeCode 需要删除的员工编码
     */
    @Override
    public void remove(EmployeeCode employeeCode) {
        employeeJpa.deleteById(employeeCode.getValue());
    }
}
