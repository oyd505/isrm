package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateEmployeeCmd extends CreateEmployeeCmd {
    @NotBlank
    private String employeeCode;
}
