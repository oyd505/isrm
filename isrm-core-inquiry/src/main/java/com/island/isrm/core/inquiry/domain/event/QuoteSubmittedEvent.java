package com.island.isrm.core.inquiry.domain.event;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 * 报价已提交事件
 *
 * @author dao.ouyang
 * @since 2024-03-23
 */
@Getter
public class QuoteSubmittedEvent extends ApplicationEvent {
    private String quoteCode;
    private String supplierCode;
    private String inquiryCode;

    public QuoteSubmittedEvent(QuoteCode quoteCode, SupplierCode supplierCode, InquiryCode inquiryCode) {
        super(UUID.randomUUID());
        this.quoteCode = quoteCode.getValue();
        this.supplierCode = supplierCode.getValue();
        this.inquiryCode = inquiryCode.getValue();
    }
}
