package com.island.isrm.core.inquiry.port.message;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class QuoteSubmittedListenerSpring implements ApplicationListener<QuoteSubmittedEvent> {
    private final InquiryAppService inquiryAppService;

    public QuoteSubmittedListenerSpring(InquiryAppService inquiryAppService) {
        this.inquiryAppService = inquiryAppService;
    }

    @Override
    public void onApplicationEvent(QuoteSubmittedEvent event) {
        this.inquiryAppService.updateQuoteProgress(event);
    }
}
