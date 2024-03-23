package com.island.isrm.core.inquiry.domain.message;

import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;

/**
 * 报价事件发布器
 *
 * @author dao.ouyang
 * @since 2024-03-23
 */
public interface QuoteEventPublisher {

    /**
     * 发布报价已提交事件
     *
     * @param event 报价已提交事件
     */
    void publishQuoteSubmittedEvent(QuoteSubmittedEvent event);
}
