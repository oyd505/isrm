package com.island.isrm.core.idaccess.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.idaccess.domain.dp.Department;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.dp.EmployeeContact;
import com.island.isrm.core.idaccess.domain.dp.JobTitle;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 员工领域实体类，继承自BaseEntity，用于表示系统中的员工信息
 * 该类主要包含了员工的基本信息，如员工编号、姓名、部门、职位和联系方式
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class Employee extends BaseEntity {
    // 员工编号，作为员工的唯一标识
    private final EmployeeCode employeeCode;
    // 员工姓名
    @Setter
    private Name name;
    // 员工所属部门
    @Setter
    private Department department;
    // 员工职位
    @Setter
    private JobTitle jobTitle;
    // 员工联系方式
    @Setter
    private EmployeeContact employeeContact;

    /**
     * 构造方法，初始化员工对象
     *
     * @param employeeCode 员工编号，作为员工的唯一标识
     */
    public Employee(EmployeeCode employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * 更新员工信息
     *
     * @param employee 需要更新的员工信息对象
     *                 通过将传入的员工信息对象的属性值复制到当前对象，以达到更新员工信息的目的
     */
    public void update(Employee employee) {
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.jobTitle = employee.getJobTitle();
        this.employeeContact = employee.getEmployeeContact();
    }
}
