package com.island.isrm.core.common.domain.event;

import lombok.Getter;

@Getter
public class SupplierCreatedEvent extends DomainEvent {
    private final String supplierCode;
    private final String supplierName;

    public SupplierCreatedEvent(Object source, String supplierCode, String supplierName) {
        super(source);
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
    }
}
