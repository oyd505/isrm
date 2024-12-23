package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

public class EmployeeCode extends Code {
    public EmployeeCode(String value) {
        super(value);
        Assert.hasText(value, "员工编码不能为空");
    }
}
   