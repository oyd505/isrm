package com.island.isrm.core.idaccess.domain.dp;

import lombok.Data;

@Data
public class UserStatus {
    private final String roles;
    private final boolean disabled;
    private final boolean accountLocked;

}
