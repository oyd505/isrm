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

    /**
     * 构造方法，初始化供应商实例
     * 
     * @param supplierCode 供应商编码
     * @param supplierStatus 供应商状态
     */
    public Supplier(SupplierCode supplierCode, SupplierStatus supplierStatus) {
        this.supplierCode = supplierCode;
        this.supplierStatus = supplierStatus;
    }

    /**
     * 检查供应商是否可编辑
     * 根据供应商状态判断是否允许进行编辑操作，如果不允许，则抛出业务异常
     * 
     * @throws BizException 如果供应商状态不允许编辑，则抛出此异常
     */
    public void checkEditable() {
        if (!this.supplierStatus.isEditable()) {
            throw new BizException(SupplierStatus.POTENTIAL.name() + " 状态的供应商才允许编辑操作");
        }
    }

    /**
     * 更新供应商信息
     * 在确保当前供应商可编辑的前提下，用另一个供应商实例的信息来更新当前供应商的信息
     * 
     * @param supplier 包含更新信息的供应商实例
     */
    public void update(Supplier supplier) {
        this.checkEditable();
        this.name = supplier.getName();
    }

    /**
     * 添加联系人
     * 将一个新的联系人添加到供应商的联系人列表中
     * 
     * @param supplierContact 要添加的供应商联系人实例
     */
    public void addContact(SupplierContact supplierContact) {
        this.supplierContacts.add(supplierContact);
    }

    /**
     * 更新联系人信息
     * 根据联系人的唯一标识符找到供应商的对应联系人，并用新的联系人信息进行更新
     * 
     * @param supplierContact 包含更新信息的供应商联系人实例
     */
    public void updateContact(SupplierContact supplierContact) {
        Optional<SupplierContact> supplierContactOptional = this.supplierContacts.stream()
                .filter(contact -> contact.getSupplierContactId().equals(supplierContact.getSupplierContactId()))
                .findFirst();
        supplierContactOptional.ifPresent(contact -> contact.update(supplierContact));
    }
}
