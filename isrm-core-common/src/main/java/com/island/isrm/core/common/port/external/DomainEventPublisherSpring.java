package com.island.isrm.core.common.port.external;

import com.island.isrm.core.common.domain.event.DomainEvent;
import com.island.isrm.core.common.domain.external.DomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 使用Spring的事件发布机制来实现领域事件发布器
 * 这个类适配了Spring的ApplicationEventPublisher到我们的DomainEventPublisher接口
 * 以便在领域模型中以领域事件的方式发布事件
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Component
public class DomainEventPublisherSpring implements DomainEventPublisher {
    // Spring应用事件发布器，用于实际发布事件
    private final ApplicationEventPublisher publisher;

    /**
     * 构造函数注入Spring的ApplicationEventPublisher
     *
     * @param publisher Spring框架的ApplicationEventPublisher实例，用于发布应用事件
     */
    public DomainEventPublisherSpring(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 发布一个领域事件
     * 该方法将领域事件委托给Spring的ApplicationEventPublisher进行发布
     *
     * @param event 要发布的领域事件实例
     */
    @Override
    public void publishEvent(DomainEvent event) {
        this.publisher.publishEvent(event);
    }

}
