package com.island.isrm.core.common.domain.event;

import lombok.Getter;

@Getter
public class SupplierContactCreatedEvent extends DomainEvent {
    private final String supplierCode;
    private final String supplierName;
    private final String phone;
    private final String contactName;

    public SupplierContactCreatedEvent(Object source, String supplierCode, String supplierName, String phone, String contactName) {
        super(source);
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.phone = phone;
        this.contactName = contactName;
    }
}
