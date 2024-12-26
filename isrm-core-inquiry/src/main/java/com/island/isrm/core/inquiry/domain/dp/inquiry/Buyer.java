package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

/**
 * 采购员类
 * 用于表示采购员的基本信息，包括采购员的编码和名称
 * 
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Buyer {
    /**
     * 采购员编码
     * 用于唯一标识一个采购员
     */
    private final String code;
    
    /**
     * 采购员名称
     * 表示采购员的名称，便于人类识别
     */
    private final String name;
}
