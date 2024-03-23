package com.island.isrm.core.inquiry.domain.dp.inquiry;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 询价编码
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class InquiryCode extends Code {
    public InquiryCode(String value) {
        super(value);
        Assert.hasText(value, "询价编码不能为空");
    }
}
