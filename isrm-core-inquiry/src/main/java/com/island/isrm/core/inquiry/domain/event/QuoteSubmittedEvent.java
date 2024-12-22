package com.island.isrm.core.inquiry.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 报价已提交事件
 *
 * @author dao.ouyang
 * @since 2024-03-23
 */
@Getter
public class QuoteSubmittedEvent extends ApplicationEvent {
    private final String quoteCode;
    private final String supplierCode;
    private final String inquiryCode;

    public QuoteSubmittedEvent(Object source, String quoteCode, String supplierCode, String inquiryCode) {
        super(source);
        this.quoteCode = quoteCode;
        this.supplierCode = supplierCode;
        this.inquiryCode = inquiryCode;
    }
}
