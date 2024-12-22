package com.island.isrm.core.supplier.port.repo.dao.projection;

/**
 * 供应商联系人基本信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface SupplierContactBasic {
    /**
     * 获取联系人ID
     *
     * @return 联系人ID
     */
    Long getId();

    /**
     * 获取联系人姓名
     *
     * @return 联系人姓名
     */
    String getName();

    /**
     * 获取联系人电话号码
     *
     * @return 联系人电话号码
     */
    String getPhone();

    /**
     * 获取供应商编码
     *
     * @return 供应商编码
     */
    String getSupplierCode();
}
