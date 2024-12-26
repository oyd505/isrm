package com.island.isrm.core.common.domain.event;

import lombok.Getter;

/**
 * 员工创建事件类，用于表示员工创建的领域事件
 * 继承自DomainEvent，以获取事件源的信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Getter
public class EmployeeCreatedEvent extends DomainEvent {
    // 员工编码，用于唯一标识员工
    private final String employeeCode;
    // 员工姓名，用于记录员工的名称
    private final String employeeName;

    /**
     * 构造函数，初始化员工创建事件
     *
     * @param source       事件源，表示触发事件的对象
     * @param employeeCode 员工编码，用于唯一标识员工
     * @param employeeName 员工姓名，用于记录员工的名称
     */
    public EmployeeCreatedEvent(Object source, String employeeCode, String employeeName) {
        super(source);
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
    }
}
