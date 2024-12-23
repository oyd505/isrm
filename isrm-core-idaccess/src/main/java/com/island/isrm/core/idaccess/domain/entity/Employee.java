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
 * 员工
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class Employee extends BaseEntity {
    private final EmployeeCode employeeCode;
    @Setter
    private Name name;
    @Setter
    private Department department;
    @Setter
    private JobTitle jobTitle;
    @Setter
    private EmployeeContact employeeContact;


    public Employee(EmployeeCode employeeCode) {
        this.employeeCode = employeeCode;
    }

    public void update(Employee employee) {
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.jobTitle = employee.getJobTitle();
        this.employeeContact = employee.getEmployeeContact();
    }
}
