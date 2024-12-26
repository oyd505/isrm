package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 更新密码命令类
 * 用于封装用户更新密码时所需的老密码和新密码信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class UpdatePasswordCmd {
    /**
     * 旧密码字段，不能为空
     * 用于验证用户是否知道当前的密码
     */
    @NotBlank
    private String oldPassword;

    /**
     * 新密码字段，不能为空
     * 用于设置用户的新密码，以更新其账户信息
     */
    @NotBlank
    private String newPassword;
}
