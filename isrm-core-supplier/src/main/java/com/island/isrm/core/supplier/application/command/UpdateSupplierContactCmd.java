package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新供应商联系人命令类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSupplierContactCmd extends AddSupplierContactCmd {
    /**
     * 供应商联系人ID
     */
    @NotNull
    @Positive
    private Long id;
}
