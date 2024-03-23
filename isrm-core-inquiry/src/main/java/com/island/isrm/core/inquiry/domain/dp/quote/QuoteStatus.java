package com.island.isrm.core.inquiry.domain.dp.quote;

/**
 * 报价请求状态
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public enum QuoteStatus {
    /**
     * 未提交状态
     */
    PENDING,
    /**
     * 已提交状态
     */
    SUBMITTED,
    /**
     * 已取消状态
     */
    CANCELED
}
