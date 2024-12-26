package com.island.isrm.core.supplier.domain.repo;

import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.entity.Supplier;

/**
 * 供应商仓储
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface SupplierRepository {
    /**
     * 根据供应商编码查询供应商
     *
     * @param supplierCode 供应商编码
     * @return 供应商
     */
    Supplier find(SupplierCode supplierCode);

    /**
     * 新增供应商
     *
     * @param supplier 供应商
     * @return 供应商编码
     */
    SupplierCode add(Supplier supplier);

    void update(Supplier supplier);

    /**
     * 删除供应商及其关联联系人
     *
     * @param supplierCode 供应商编码
     */
    void removeAll(SupplierCode supplierCode);

    /**
     * 根据供应商编码和供应商联系人ID查询供应商联系人
     *
     * @param supplierCode      供应商编码
     * @param supplierContactId 供应商联系人ID
     * @return 供应商带有一个供应商联系人
     */
    Supplier findOneSupplierContact(SupplierCode supplierCode, SupplierContactId supplierContactId);

    /**
     * 添加一个联系人到供应商
     *
     * @param supplier 供应商
     * @return 供应商联系人ID
     */
    SupplierContactId addOneSupplierContact(Supplier supplier);

    /**
     * 更新供应商联系人
     *
     * @param supplier 供应商
     */
    void updateSupplierContact(Supplier supplier);

    /**
     * 移除一个供应商联系人
     *
     * @param supplierContactId 供应商联系人ID
     */
    void removeOneSupplierContact(SupplierContactId supplierContactId);
}
