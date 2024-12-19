package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSupplierCmd extends CreateSupplierCmd {
    @NotBlank
    private String supplierCode;
}
