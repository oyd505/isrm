package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

public class UserName extends Code {
    public UserName(String value) {
        super(value);
        Assert.hasText(value, "用户名不能为空");
    }
}
