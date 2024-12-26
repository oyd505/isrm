package com.island.isrm.core.idaccess.application.command;

import com.island.isrm.core.idaccess.domain.dp.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建用户命令类
 * 用于处理用户创建请求中的数据
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class CreateUserCmd {
    /**
     * 用户昵称，不能为空
     * 用于标识用户的昵称或显示名称
     */
    @NotBlank
    private String nickname;

    /**
     * 用户类型，不能为空
     * 用于标识用户的角色或类型，如管理员、外部用户等
     */
    @NotBlank
    private String userType;

    /**
     * 供应商编码
     * 对于外部用户，用于标识其所属的供应商
     */
    private String supplierCode;

    /**
     * 供应商名称
     * 对于外部用户，用于标识其所属供应商的名称
     */
    private String supplierName;

    /**
     * 员工编码
     * 对于内部用户，用于标识其在组织内的唯一编码
     */
    private String employeeCode;

    /**
     * 员工名称
     * 对于内部用户，用于标识其在组织内的名称
     */
    private String employeeName;

    /**
     * 根据用户类型生成用户名
     *
     * @return 生成的用户名
     * 对于不同类型的用户，返回不同的标识：
     * - 管理员返回昵称
     * - 外部用户返回供应商编码
     * - 其他用户返回员工编码
     */
    public String generateUserName() {
        if (UserType.valueOf(userType).isAdmin()) {
            return nickname;
        } else if (UserType.valueOf(userType).isExternal()) {
            return supplierCode;
        }
        return employeeCode;
    }
}
