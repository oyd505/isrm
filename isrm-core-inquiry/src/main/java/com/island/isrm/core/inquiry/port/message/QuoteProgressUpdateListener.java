package com.island.isrm.core.inquiry.port.message;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuoteProgressUpdateListener {
    private final InquiryAppService inquiryAppService;

    public QuoteProgressUpdateListener(InquiryAppService inquiryAppService) {
        this.inquiryAppService = inquiryAppService;
    }

    @EventListener
    public void handleQuoteSubmittedEvent(QuoteSubmittedEvent event) {
        this.inquiryAppService.updateQuoteProgress(event);
    }
}
