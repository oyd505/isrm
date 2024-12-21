package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordCmd {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
