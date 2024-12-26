package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新员工信息的命令类
 * 继承自CreateEmployeeCmd，以复用创建员工时的属性和逻辑
 * 主要新增了员工编码字段，用于标识要更新的员工
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateEmployeeCmd extends CreateEmployeeCmd {
    /**
     * 需要更新的员工的编码
     * 使用NotBlank注解确保员工编码不为空，保证数据的完整性
     */
    @NotBlank
    private String employeeCode;
}
