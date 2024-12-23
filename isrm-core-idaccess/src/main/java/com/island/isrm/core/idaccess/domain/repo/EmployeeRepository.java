package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.entity.Employee;

/**
 * 员工仓库
 */
public interface EmployeeRepository {
    Employee find(EmployeeCode employeeCode);

    EmployeeCode add(Employee employee);

    void update(Employee employee);

    void remove(EmployeeCode employeeCode);
}
