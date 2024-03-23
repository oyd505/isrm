package com.island.isrm.core.inquiry.domain.dp.inquiry;

/**
 * 询价状态
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public enum InquiryStatus {

    /**
     * 未提交状态
     */
    PENDING {
        @Override
        public boolean isEditable() {
            return true;
        }
    },
    /**
     * 已提交状态
     */
    SUBMITTED,
    /**
     * 已发布状态
     */
    PUBLISHED,
    /**
     * 已确认状态
     */
    CONFIRMED,
    /**
     * 已确认待付款状态
     */
    PAID_PENDING,
    /**
     * 已付款状态
     */
    PAID,
    /**
     * 已取消状态
     */
    CANCELED;

    public boolean isEditable() {
        return false;
    }
}
