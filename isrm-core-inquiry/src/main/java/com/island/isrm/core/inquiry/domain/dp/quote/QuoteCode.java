package com.island.isrm.core.inquiry.domain.dp.quote;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 报价编码
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class QuoteCode extends Code {
    public QuoteCode(String value) {
        super(value);
        Assert.hasText(value, "报价编码不能为空");
    }
}
