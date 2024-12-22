/**
 * 供应商编码和名称接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.supplier.port.repo.dao.projection;

public interface SupplierCodeAndName {

    /**
     * 获取供应商编码
     * @return 供应商编码字符串
     */
    String getCode();

    /**
     * 获取供应商名称
     * @return 供应商名称字符串
     */
    String getName();
}
