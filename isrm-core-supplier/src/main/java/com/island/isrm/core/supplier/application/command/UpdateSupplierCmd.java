package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新供应商命令类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSupplierCmd extends CreateSupplierCmd {
    /**
     * 需要更新的供应商编码
     */
    @NotBlank
    private String supplierCode;
}
