package com.island.isrm.core.common.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SupplierCreatedEvent extends ApplicationEvent {
    private final String supplierCode;
    private final String supplierName;

    public SupplierCreatedEvent(Object source, String supplierCode, String supplierName) {
        super(source);
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
    }
}
