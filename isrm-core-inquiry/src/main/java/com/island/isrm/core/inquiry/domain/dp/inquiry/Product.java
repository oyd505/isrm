package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

/**
 * 商品类
 * 用于表示商品的基本信息，包括商品编码和名称
 * 
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Product {
    /**
     * 商品编码
     * 用于唯一标识一个商品
     */
    private final String code;
    
    /**
     * 商品名称
     * 描述商品的名称
     */
    private final String name;
}
