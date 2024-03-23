package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

public class UserCode extends Code {
    public UserCode(String value) {
        super(value);
        Assert.hasText(value, "用户编码不能为空");
    }
}
