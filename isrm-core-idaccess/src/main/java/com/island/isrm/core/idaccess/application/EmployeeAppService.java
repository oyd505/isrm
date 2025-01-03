package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.idaccess.application.command.CreateEmployeeCmd;
import com.island.isrm.core.idaccess.application.command.UpdateEmployeeCmd;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import org.springframework.validation.annotation.Validated;

/**
 * 员工应用服务接口
 * 提供员工相关应用层服务，包括创建、修改和删除员工
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Validated
public interface EmployeeAppService {

    /**
     * 创建员工
     *
     * @param command 创建员工命令对象
     * @return 新增员工工号
     */
    EmployeeCode create(CreateEmployeeCmd command);

    /**
     * 修改员工
     *
     * @param command 更新员工命令对象
     */
    void update(UpdateEmployeeCmd command);

    /**
     * 删除员工
     *
     * @param employeeCode 员工工号
     */
    void remove(EmployeeCode employeeCode);
}
