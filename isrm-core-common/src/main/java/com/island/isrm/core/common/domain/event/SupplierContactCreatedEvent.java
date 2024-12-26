package com.island.isrm.core.common.domain.event;

import lombok.Getter;

/**
 * 供应商联系人创建事件
 * 当供应商联系人被创建时，该事件会被触发
 * 它封装了与供应商联系人创建相关的所有信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Getter
public class SupplierContactCreatedEvent extends DomainEvent {
    /**
     * 供应商编码
     */
    private final String supplierCode;
    /**
     * 供应商名称
     */
    private final String supplierName;
    /**
     * 联系电话
     */
    private final String phone;
    /**
     * 联系人姓名
     */
    private final String contactName;

    /**
     * 构造供应商联系人创建事件
     *
     * @param source       事件源，通常是指触发该事件的对象
     * @param supplierCode 供应商编码
     * @param supplierName 供应商名称
     * @param phone        联系电话
     * @param contactName  联系人姓名
     */
    public SupplierContactCreatedEvent(Object source, String supplierCode, String supplierName, String phone, String contactName) {
        super(source);
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.phone = phone;
        this.contactName = contactName;
    }
}
