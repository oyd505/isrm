package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 添加供应商联系人命令类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
public class AddSupplierContactCmd {
    /**
     * 联系人姓名
     */
    @NotBlank
    private String name;

    /**
     * 联系人电话
     */
    @NotBlank
    private String phone;
}
