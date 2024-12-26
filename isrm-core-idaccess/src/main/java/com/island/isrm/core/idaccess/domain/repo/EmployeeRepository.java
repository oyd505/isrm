package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.entity.Employee;

/**
 * 员工仓储接口定义了对员工实体进行 CRUD 操作的方法
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface EmployeeRepository {
    /**
     * 根据员工编码查找员工
     *
     * @param employeeCode 员工编码
     * @return 如果找到则返回员工实体，否则返回 null
     */
    Employee find(EmployeeCode employeeCode);

    /**
     * 向系统中添加新员工
     *
     * @param employee 要添加的员工实体
     * @return 添加到系统中的员工的唯一编码
     */
    EmployeeCode add(Employee employee);

    /**
     * 更新系统中的员工信息
     *
     * @param employee 包含更新信息的员工实体
     */
    void update(Employee employee);

    /**
     * 根据员工编码从系统中移除员工
     *
     * @param employeeCode 要移除的员工的编码
     */
    void remove(EmployeeCode employeeCode);
}
