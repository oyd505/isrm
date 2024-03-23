package com.island.isrm.core.inquiry.domain.external;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;

/**
 * 报价使用的询价服务
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public interface QuoteInquiryService {
    /**
     * 创建报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @param quoteCode    报价编码
     * @return 报价请求
     */
    QuoteRequest createQuote(InquiryCode inquiryCode, SupplierCode supplierCode, QuoteCode quoteCode);

    /**
     * 检查是否可报价
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     */
    void checkQuoteEnabled(InquiryCode inquiryCode, SupplierCode supplierCode);
}
