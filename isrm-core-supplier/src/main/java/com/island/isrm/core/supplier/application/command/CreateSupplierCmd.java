package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建供应商命令类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
public class CreateSupplierCmd {
    /**
     * 供应商名称
     */
    @NotBlank
    private String name;
}
