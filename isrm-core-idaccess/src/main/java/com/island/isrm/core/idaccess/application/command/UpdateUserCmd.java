package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新用户命令类，继承自CreateUserCmd
 * 用于处理用户信息更新操作，确保用户名称不为空
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserCmd extends CreateUserCmd {
    /**
     * 用户名，更新用户时必须提供，且不能为空
     */
    @NotBlank
    private String userName;
}
