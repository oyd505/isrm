package com.island.isrm.core.inquiry.port.message;

import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import com.island.isrm.core.inquiry.domain.message.QuoteEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class QuoteEventPublisherSpring implements QuoteEventPublisher, ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishQuoteSubmittedEvent(QuoteSubmittedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
