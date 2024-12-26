package com.island.isrm.core.common.domain.event;

import org.springframework.context.ApplicationEvent;

/**
 * 领域事件抽象类，继承自Spring的ApplicationEvent
 * 用于在领域驱动设计中实现领域事件，以支持领域逻辑的发布和订阅机制
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public abstract class DomainEvent extends ApplicationEvent {
    /**
     * 构造函数，初始化领域事件
     *
     * @param source 事件源，通常是触发事件的对象
     */
    public DomainEvent(Object source) {
        super(source);
    }
}
