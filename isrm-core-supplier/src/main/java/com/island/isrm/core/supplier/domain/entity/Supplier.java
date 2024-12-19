package com.island.isrm.core.supplier.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 供应商
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class Supplier extends BaseEntity {

    // 供应商编码
    private final SupplierCode supplierCode;
    // 供应商名称
    @Setter
    private Name name;
    // 供应商状态
    private final SupplierStatus supplierStatus;
    // 供应商联系人列表
    private final List<SupplierContact> supplierContacts = new ArrayList<>();

    public Supplier(SupplierCode supplierCode, SupplierStatus supplierStatus) {
        this.supplierCode = supplierCode;
        this.supplierStatus = supplierStatus;
    }

    public void checkEditable() {
        if (!this.supplierStatus.isEditable()) {
            throw new BizException(SupplierStatus.POTENTIAL.name() + " 状态的供应商才允许编辑操作");
        }
    }

    public void update(Supplier supplier) {
        this.checkEditable();
        this.name = supplier.getName();
    }

    public void addContact(SupplierContact supplierContact) {
        this.supplierContacts.add(supplierContact);
    }

    public void updateContact(SupplierContact supplierContact) {
        Optional<SupplierContact> supplierContactOptional = this.supplierContacts.stream()
                .filter(contact -> contact.getSupplierContactId().equals(supplierContact.getSupplierContactId()))
                .findFirst();
        supplierContactOptional.ifPresent(contact -> contact.update(supplierContact));
    }
}
