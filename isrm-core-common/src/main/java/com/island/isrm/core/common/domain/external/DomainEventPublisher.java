/**
 * 发布领域事件的接口
 * 该接口定义了发布领域事件的方法，领域事件是在领域模型中发生的 noteworthy 事件
 * 它们通常用于在不同的聚合或子系统之间传播信息，而不需要直接耦合这些组件
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
package com.island.isrm.core.common.domain.external;

import com.island.isrm.core.common.domain.event.DomainEvent;

/**
 * 领域事件发布者接口
 * 该接口提供了一个方法来发布领域事件，使得其他组件可以订阅并响应这些事件
 */
public interface DomainEventPublisher {

    /**
     * 发布一个领域事件
     * 当领域内发生重要事件时，通过此方法发布事件，通知所有订阅该事件的监听器
     *
     * @param event 要发布的领域事件，不能为空
     */
    void publishEvent(DomainEvent event);
}
