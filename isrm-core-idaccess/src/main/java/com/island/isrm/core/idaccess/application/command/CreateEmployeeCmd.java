package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeCmd {

    @NotBlank
    private String name;
    private String departmentCode;
    private String departmentName;
    private String jobTitle;
    private String phone;
    private String email;
}
