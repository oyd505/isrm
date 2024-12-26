package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.util.Objects;

/**
 * 供应商类，用于表示和操作供应商信息
 * 供应商由供应商编码和名称唯一标识
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Supplier {
    /**
     * 供应商编码，用于唯一标识供应商
     */
    private final SupplierCode code;
    
    /**
     * 供应商名称，用于显示供应商的名称
     */
    private final String name;

    /**
     * 检查当前供应商是否与给定的供应商编码匹配
     * 
     * @param supplierCode 给定的供应商编码，用于比较
     * @return 如果当前供应商的编码与给定的供应商编码匹配，则返回true；否则返回false
     */
    public boolean isMatched(SupplierCode supplierCode) {
        // 检查当前供应商的编码或给定的供应商编码是否为null，如果任一为null，则返回false
        if (Objects.isNull(code) || Objects.isNull(supplierCode)) {
            return false;
        }
        // 比较当前供应商的编码与给定的供应商编码的值是否相等
        return code.getValue().equals(supplierCode.getValue());
    }
}
