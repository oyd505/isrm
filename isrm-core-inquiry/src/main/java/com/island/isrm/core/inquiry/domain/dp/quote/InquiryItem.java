package com.island.isrm.core.inquiry.domain.dp.quote;

import lombok.Data;

/**
 * 询价项类，用于表示询价过程中的具体商品信息
 * 
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class InquiryItem {
    /**
     * 询价行号，用于标识询价单中的每一项商品
     */
    private final Integer inquiryLine;
    
    /**
     * 商品编码，用于唯一标识商品的信息
     */
    private final String productCode;
    
    /**
     * 商品名称，描述商品的名称信息
     */
    private final String productName;
}
