package com.island.isrm.core.inquiry.domain.event;

import com.island.isrm.core.common.domain.event.DomainEvent;
import lombok.Getter;

/**
 * 报价已提交事件
 *
 * 该事件表示供应商已经提交了报价，用于在系统中传播报价提交的信息
 * 
 * @author dao.ouyang
 * @since 2024-03-23
 */
@Getter
public class QuoteSubmittedEvent extends DomainEvent {
    // 报价单编码
    private final String quoteCode;
    // 供应商编码
    private final String supplierCode;
    // 询价单编码
    private final String inquiryCode;

    /**
     * 构造函数
     *
     * @param source 事件源，通常是指触发该事件的对象
     * @param quoteCode 报价单编码
     * @param supplierCode 供应商编码
     * @param inquiryCode 询价单编码
     */
    public QuoteSubmittedEvent(Object source, String quoteCode, String supplierCode, String inquiryCode) {
        super(source);
        this.quoteCode = quoteCode;
        this.supplierCode = supplierCode;
        this.inquiryCode = inquiryCode;
    }
}
