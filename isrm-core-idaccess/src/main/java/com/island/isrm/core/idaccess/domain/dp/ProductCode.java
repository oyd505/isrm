package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

public class ProductCode extends Code {
    public ProductCode(String value) {
        super(value);
        Assert.hasText(value, "商品编码不能为空"); // 验证产品编码不能为空
    }
} 