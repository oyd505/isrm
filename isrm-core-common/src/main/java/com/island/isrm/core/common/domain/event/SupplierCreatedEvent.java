package com.island.isrm.core.common.domain.event;

import lombok.Getter;

/**
 * 供应商创建事件类，继承自DomainEvent
 * 用于表示供应商创建的领域事件，封装了供应商的基本信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Getter
public class SupplierCreatedEvent extends DomainEvent {
    // 供应商编码
    private final String supplierCode;
    // 供应商名称
    private final String supplierName;

    /**
     * 构造函数，初始化供应商创建事件
     *
     * @param source       事件源，通常是指触发该事件的对象
     * @param supplierCode 供应商编码，用于唯一标识供应商
     * @param supplierName 供应商名称，用于显示供应商的信息
     */
    public SupplierCreatedEvent(Object source, String supplierCode, String supplierName) {
        super(source);
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
    }
}
