package com.island.isrm.core.idaccess.application.command;

import com.island.isrm.core.idaccess.domain.dp.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CreateUserCmd {
    @NotBlank
    private String nickname;
    @NotBlank
    private String userType;
    private String supplierCode;
    private String supplierName;
    private String employeeCode;
    private String employeeName;
    private String phone;

    public String getUserName() {
        if (UserType.valueOf(userType).isAdmin()) {
            return nickname;
        } else if (UserType.valueOf(userType).isExternal()) {
            if (StringUtils.hasText(phone)) {
                return phone;
            }
            return supplierCode;
        }
        return employeeCode;
    }
}
