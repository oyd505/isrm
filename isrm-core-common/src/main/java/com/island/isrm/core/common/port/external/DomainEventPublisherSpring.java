package com.island.isrm.core.common.port.external;

import com.island.isrm.core.common.domain.event.DomainEvent;
import com.island.isrm.core.common.domain.external.DomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisherSpring implements DomainEventPublisher {
    private final ApplicationEventPublisher publisher;

    public DomainEventPublisherSpring(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publishEvent(DomainEvent event) {
        this.publisher.publishEvent(event);
    }

}
