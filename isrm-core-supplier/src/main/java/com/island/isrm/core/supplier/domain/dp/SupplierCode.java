package com.island.isrm.core.supplier.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 供应商编码
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public class SupplierCode extends Code {
    public SupplierCode(String value) {
        super(value);
        Assert.hasText(value, "供应商编码不能为空");
    }
}
