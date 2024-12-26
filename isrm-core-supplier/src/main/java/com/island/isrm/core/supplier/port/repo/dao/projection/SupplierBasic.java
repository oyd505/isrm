package com.island.isrm.core.supplier.port.repo.dao.projection;

/**
 * 供应商基本信息接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface SupplierBasic {

    /**
     * 获取供应商编码
     *
     * @return 供应商编码
     */
    String getSupplierCode();

    /**
     * 获取供应商名称
     *
     * @return 供应商名称
     */
    String getName();

    /**
     * 获取供应商状态
     *
     * @return 供应商状态
     */
    String getSupplierStatus();
}
