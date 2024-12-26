package com.island.isrm.core.inquiry.port.message;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 报价进度更新监听器
 * 监听报价提交事件，并更新报价进度
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Component
public class QuoteProgressUpdateListener {
    /**
     * 报价应用服务
     */
    private final InquiryAppService inquiryAppService;

    /**
     * 构造函数，注入报价应用服务
     *
     * @param inquiryAppService 报价应用服务
     */
    public QuoteProgressUpdateListener(InquiryAppService inquiryAppService) {
        this.inquiryAppService = inquiryAppService;
    }

    /**
     * 处理报价提交事件
     * 当报价提交事件触发时，调用此方法更新报价进度
     *
     * @param event 报价提交事件，包含需要更新的报价信息
     */
    @EventListener
    public void handleQuoteSubmittedEvent(QuoteSubmittedEvent event) {
        this.inquiryAppService.updateQuoteProgress(event);
    }
}
