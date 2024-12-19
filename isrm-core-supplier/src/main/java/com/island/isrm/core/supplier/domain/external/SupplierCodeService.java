package com.island.isrm.core.supplier.domain.external;

import com.island.isrm.core.supplier.domain.dp.SupplierCode;

/**
 * 供应商编码服务
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface SupplierCodeService {

    /**
     * 生成供应商编码
     *
     * @return 供应商编码
     */
    SupplierCode generateSerialNumber();
}
