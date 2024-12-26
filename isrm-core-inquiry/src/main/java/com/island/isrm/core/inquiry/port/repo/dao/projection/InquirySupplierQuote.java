package com.island.isrm.core.inquiry.port.repo.dao.projection;

/**
 * 供应商报价信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface InquirySupplierQuote {
    /**
     * 获取供应商ID
     *
     * @return 供应商ID
     */
    Long getId();

    /**
     * 获取供应商名称
     *
     * @return 供应商名称
     */
    String getName();

    /**
     * 获取供应商联系人姓名
     *
     * @return 供应商联系人姓名
     */
    String getContactName();

    /**
     * 获取审核状态
     *
     * @return 审核状态
     */
    String getAudited();

    /**
     * 获取确认状态
     *
     * @return 确认状态
     */
    String getConfirmed();

    /**
     * 获取报价数量
     *
     * @return 报价数量
     */
    Integer getQuoteCount();

    /**
     * 获取报价编码
     *
     * @return 报价编码
     */
    String getQuoteCode();
}
