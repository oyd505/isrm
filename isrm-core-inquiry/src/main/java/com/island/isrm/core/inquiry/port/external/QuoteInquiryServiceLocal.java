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

@Component
public class QuoteInquiryServiceLocal implements QuoteInquiryService {
    private final InquiryRequestRepository inquiryRequestRepository;

    public QuoteInquiryServiceLocal(InquiryRequestRepository inquiryRequestRepository) {
        this.inquiryRequestRepository = inquiryRequestRepository;
    }

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

    @Override
    public void checkQuoteEnabled(InquiryCode inquiryCode, SupplierCode supplierCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(inquiryCode, supplierCode);
        inquiryRequest.checkQuoteEnabled(supplierCode);
    }
}
