package com.island.isrm.core.inquiry.domain.event;

import com.island.isrm.core.common.domain.event.DomainEvent;
import lombok.Getter;

/**
 * 报价已提交事件
 *
 * @author dao.ouyang
 * @since 2024-03-23
 */
@Getter
public class QuoteSubmittedEvent extends DomainEvent {
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
