package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

/**
 * 采购组织类，用于表示和操作采购组织的相关信息
 * 该类采用了Lombok的@Data注解，自动生成getter和setter方法，简化编码
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class PurchasingOrg {
    /**
     * 采购组织的编码，用于唯一标识一个采购组织
     */
    private final String code;
    
    /**
     * 采购组织的名称，用于显示采购组织的全称
     */
    private final String name;
}
