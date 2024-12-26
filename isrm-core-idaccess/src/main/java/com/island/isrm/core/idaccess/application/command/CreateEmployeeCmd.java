package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建员工命令类
 * 用于处理创建新员工时所需的信息和参数
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class CreateEmployeeCmd {

    /**
     * 员工姓名，不能为空
     * 使用@NotBlank注解确保在输入时必须提供员工姓名
     */
    @NotBlank
    private String name;

    /**
     * 部门编码，可能为空
     * 用于标识员工所属部门的编码
     */
    private String departmentCode;

    /**
     * 部门名称，可能为空
     * 用于标识员工所属部门的名称
     */
    private String departmentName;

    /**
     * 职位标题，可能为空
     * 描述员工在公司中的职位或角色
     */
    private String jobTitle;

    /**
     * 电话号码，可能为空
     * 员工的联系电话，用于工作沟通
     */
    private String phone;

    /**
     * 电子邮件地址，可能为空
     * 员工的工作邮箱，用于工作相关的电子通信
     */
    private String email;
}
