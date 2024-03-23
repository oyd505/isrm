package com.island.isrm.core.inquiry.domain.repo;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;

import java.util.Optional;

/**
 * 报价申请仓库
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public interface QuoteRequestRepository {

    /**
     * 根据报价编号查找报价申请信息
     *
     * @param quoteCode 报价编号
     * @return 报价申请
     */
    QuoteRequest find(QuoteCode quoteCode);

    /**
     * 根据报价编号查找报价申请及其关联的报价项
     *
     * @param quoteCode 报价编号
     * @return 报价申请
     */
    QuoteRequest findAll(QuoteCode quoteCode);

    /**
     * 根据询价编号和供应商编号查找待处理的报价申请
     *
     * @param inquiryCode  询价编号
     * @param supplierCode 供应商代码
     * @return 报价申请
     */
    Optional<QuoteRequest> findOnlyPending(InquiryCode inquiryCode, SupplierCode supplierCode);

    /**
     * 根据询价编号和供应商编号查找已提交的报价申请
     *
     * @param inquiryCode  询价编号
     * @param supplierCode 供应商代码
     * @return 报价申请
     */
    Optional<QuoteRequest> findOnlySubmitted(InquiryCode inquiryCode, SupplierCode supplierCode);

    /**
     * 添加报价申请及其关联的报价项
     *
     * @param quoteRequest 报价申请
     * @return 报价编号
     */
    QuoteCode addAll(QuoteRequest quoteRequest);

    /**
     * 更新报价申请
     *
     * @param quoteRequest 报价申请
     */
    void update(QuoteRequest quoteRequest);

    /**
     * 删除报价申请及其关联的报价项
     *
     * @param quoteCode 报价编号
     */
    void removeAll(QuoteCode quoteCode);

    /**
     * 根据报价编号和报价项ID查找报价项
     *
     * @param quoteCode   报价编号
     * @param quoteItemId 报价项ID
     * @return 报价申请带一项报价项
     */
    QuoteRequest findOneQuoteItem(QuoteCode quoteCode, QuoteItemId quoteItemId);

    /**
     * 更新报价申请信息中的报价明细项
     *
     * @param quoteRequest 报价申请
     */
    void updateQuoteItem(QuoteRequest quoteRequest);
}
