package com.island.isrm.core.supplier.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.supplier.domain.dp.Contact;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商联系人
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class SupplierContact extends BaseEntity {
    // 供应商联系人ID
    private final SupplierContactId supplierContactId;
    // 供应商编码
    private final SupplierCode supplierCode;
    // 联系人
    @Setter
    private Contact contact;

    /**
     * 构造方法
     *
     * 初始化供应商联系人对象，设置供应商联系人ID和供应商编码
     *
     * @param supplierContactId 供应商联系人ID
     * @param supplierCode 供应商编码
     */
    public SupplierContact(SupplierContactId supplierContactId, SupplierCode supplierCode) {
        this.supplierContactId = supplierContactId;
        this.supplierCode = supplierCode;
    }

    /**
     * 更新供应商联系人信息
     *
     * 此方法用于更新供应商联系人的联系人信息部分
     * 它通过传入一个新的供应商联系人对象来更新当前对象的联系人信息
     *
     * @param supplierContact 新的供应商联系人对象，包含更新的联系人信息
     */
    public void update(SupplierContact supplierContact) {
        this.contact = supplierContact.getContact();
    }
}
