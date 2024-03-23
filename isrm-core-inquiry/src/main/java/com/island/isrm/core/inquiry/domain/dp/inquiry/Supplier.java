package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.util.Objects;

/**
 * 供应商
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Supplier {
    private final SupplierCode code;
    private final String name;

    public boolean isMatched(SupplierCode supplierCode) {
        if (Objects.isNull(code) || Objects.isNull(supplierCode)) {
            return false;
        }
        return code.getValue().equals(supplierCode.getValue());
    }
}
