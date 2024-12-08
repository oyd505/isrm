package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface QuoteAppService {

    QuoteCode create(InquiryCode inquiryCode, SupplierCode supplierCode);

    void update(@Valid UpdateQuoteRequestCmd command);

    void remove(QuoteCode quoteCode);

    void submit(QuoteCode quoteCode);

    void updateItem(String quoteCode, UpdateQuoteItemCmd command);
}
