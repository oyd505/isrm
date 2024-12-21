package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserCmd extends CreateUserCmd {
    @NotBlank
    private String userName;
}
