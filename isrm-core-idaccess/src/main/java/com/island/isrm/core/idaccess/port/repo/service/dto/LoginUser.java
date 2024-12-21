package com.island.isrm.core.idaccess.port.repo.service.dto;

import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import lombok.Data;

@Data
public class LoginUser {
    private String token;
    private String userName;
    private String userType;
    private String nickname;
    private UserSupplier userSupplier;
}
