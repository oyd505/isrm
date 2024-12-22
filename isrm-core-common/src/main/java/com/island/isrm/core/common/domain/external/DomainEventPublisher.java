package com.island.isrm.core.common.domain.external;

import com.island.isrm.core.common.domain.event.DomainEvent;

public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);
}
