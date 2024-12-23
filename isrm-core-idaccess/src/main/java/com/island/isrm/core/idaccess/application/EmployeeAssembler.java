package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.idaccess.application.command.CreateEmployeeCmd;
import com.island.isrm.core.idaccess.application.command.UpdateEmployeeCmd;
import com.island.isrm.core.idaccess.domain.dp.Department;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.dp.EmployeeContact;
import com.island.isrm.core.idaccess.domain.dp.JobTitle;
import com.island.isrm.core.idaccess.domain.entity.Employee;

public class EmployeeAssembler {

    // 单例模式，静态实例
    public final static EmployeeAssembler instance = new EmployeeAssembler();

    /**
     * 将创建员工命令转换为员工实体对象
     *
     * @param command      创建员工命令对象
     * @param employeeCode 员工编码
     * @return 转换后的员工实体对象
     */
    public Employee toAddEntity(CreateEmployeeCmd command, EmployeeCode employeeCode) {
        Employee employee = new Employee(employeeCode);
        assemble(command, employee);
        return employee;
    }

    /**
     * 将创建员工命令对象的属性复制到员工实体对象中
     *
     * @param source 创建员工命令对象
     * @param target 员工实体对象
     */
    private void assemble(CreateEmployeeCmd source, Employee target) {
        target.setName(new Name(source.getName()));
        target.setDepartment(new Department(source.getDepartmentCode(), source.getDepartmentName()));
        target.setJobTitle(new JobTitle(source.getJobTitle()));
        target.setEmployeeContact(new EmployeeContact(source.getPhone(), source.getEmail()));
    }

    /**
     * 将更新员工命令转换为员工实体对象
     *
     * @param command 更新员工命令对象
     * @return 转换后的员工实体对象
     */
    public Employee toUpdateEntity(UpdateEmployeeCmd command) {
        Employee employee = new Employee(new EmployeeCode(command.getEmployeeCode()));
        assemble(command, employee);
        return employee;
    }
}
