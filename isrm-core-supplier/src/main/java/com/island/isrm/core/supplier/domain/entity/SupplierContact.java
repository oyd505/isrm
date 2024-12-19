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

    public SupplierContact(SupplierContactId supplierContactId, SupplierCode supplierCode) {
        this.supplierContactId = supplierContactId;
        this.supplierCode = supplierCode;
    }

    public void update(SupplierContact supplierContact) {
        this.contact = supplierContact.getContact();
    }
}
