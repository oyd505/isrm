package com.island.isrm.core.common.domain.event;

import lombok.Getter;

@Getter
public class EmployeeCreatedEvent extends DomainEvent {
    private final String employeeCode;
    private final String employeeName;

    public EmployeeCreatedEvent(Object source, String employeeCode, String employeeName) {
        super(source);
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
    }
}
