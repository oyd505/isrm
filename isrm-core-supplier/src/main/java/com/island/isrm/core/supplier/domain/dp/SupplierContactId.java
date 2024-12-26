package com.island.isrm.core.supplier.domain.dp;

import lombok.Data;
import lombok.NonNull;

/**
 * 供应商联系人标识类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
public class SupplierContactId {
    /**
     * 供应商联系人的唯一标识
     */
    @NonNull
    private final Long id;
}
