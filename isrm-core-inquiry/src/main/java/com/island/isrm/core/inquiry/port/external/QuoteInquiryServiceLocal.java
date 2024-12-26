package com.island.isrm.core.inquiry.port.external;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.Supplier;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.DeliveryDeadline;
import com.island.isrm.core.inquiry.domain.dp.quote.InquiryItem;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.domain.external.QuoteInquiryService;
import com.island.isrm.core.inquiry.domain.repo.InquiryRequestRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 本地报价问询服务实现类
 * 提供创建报价和检查报价启用状态的功能
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Component
public class QuoteInquiryServiceLocal implements QuoteInquiryService {
    private final InquiryRequestRepository inquiryRequestRepository;

    public QuoteInquiryServiceLocal(InquiryRequestRepository inquiryRequestRepository) {
        this.inquiryRequestRepository = inquiryRequestRepository;
    }

    /**
     * 创建报价请求
     * 根据询价编码、供应商编码和报价编码，从询价请求中生成一个新的报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @param quoteCode    报价编码
     * @return 新创建的报价请求
     */
    @Override
    public QuoteRequest createQuote(InquiryCode inquiryCode, SupplierCode supplierCode, QuoteCode quoteCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findAll(inquiryCode);
        Supplier supplier = inquiryRequest.checkQuoteEnabled(supplierCode).getSupplier();
        QuoteRequest quoteRequest = new QuoteRequest(
                quoteCode,
                supplier,
                QuoteStatus.PENDING,
                inquiryRequest.getInquiryCode());
        quoteRequest.setCurrency(inquiryRequest.getCurrency());
        List<QuoteItem> quoteItemList = inquiryRequest.getInquiryProducts().stream().map(product -> {
            QuoteItem quoteItem = new QuoteItem(
                    null,
                    new InquiryItem(
                            product.getLineNumber().getIndex(),
                            product.getProduct().getCode(),
                            product.getProduct().getName()
                    ),
                    quoteCode
            );
            quoteItem.setQuoteQuantity(product.getQuantity());
            quoteItem.setDeliveryDeadline(new DeliveryDeadline(product.getDeliveryDate().getDate()));
            return quoteItem;
        }).toList();
        quoteRequest.getQuoteItemList().addAll(quoteItemList);
        return quoteRequest;
    }

    /**
     * 检查报价启用状态
     * 确保指定的供应商对给定的询价编码启用了报价功能
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     */
    @Override
    public void checkQuoteEnabled(InquiryCode inquiryCode, SupplierCode supplierCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(inquiryCode, supplierCode);
        inquiryRequest.checkQuoteEnabled(supplierCode);
    }
}
