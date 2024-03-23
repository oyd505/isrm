package com.island.isrm.core.inquiry.domain.dp.quote;

import lombok.Data;

/**
 * 询价项
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class InquiryItem {
    private final Integer inquiryLine;
    private final String productCode;
    private final String productName;
}
