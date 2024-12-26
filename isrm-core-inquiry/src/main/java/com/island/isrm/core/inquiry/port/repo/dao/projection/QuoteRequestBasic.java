package com.island.isrm.core.inquiry.port.repo.dao.projection;

/**
 * 报价请求基础信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface QuoteRequestBasic {
    /**
     * 获取报价编码
     *
     * @return 报价编码
     */
    String getQuoteCode();

    /**
     * 获取供应商名称
     *
     * @return 供应商名称
     */
    String getSupplierName();

    /**
     * 获取报价请求状态
     *
     * @return 状态
     */
    String getStatus();

    /**
     * 获取询价编码
     *
     * @return 询价编码
     */
    String getInquiryCode();
}
