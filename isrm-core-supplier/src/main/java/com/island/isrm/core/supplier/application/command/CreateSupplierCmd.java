package com.island.isrm.core.supplier.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSupplierCmd {
    @NotBlank
    private String name;
}
