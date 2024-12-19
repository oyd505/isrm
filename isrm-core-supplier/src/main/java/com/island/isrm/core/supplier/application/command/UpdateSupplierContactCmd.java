package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSupplierContactCmd extends AddSupplierContactCmd {
    @NotNull
    @Positive
    private Long id;
}
