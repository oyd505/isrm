package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryProductId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquirySupplierId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface InquiryAppService {

    InquiryCode create(@Valid CreateInquiryRequestCmd command);

    void update(@Valid UpdateInquiryRequestCmd command);

    void remove(InquiryCode inquiryCode);

    void submit(InquiryCode inquiryCode);

    void publish(InquiryCode inquiryCode);

    Long addProduct(@Valid AddInquiryProductCmd command);

    void updateProduct(@Valid UpdateInquiryProductCmd command);

    void removeProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId);

    Long inviteSupplier(@Valid AddInquirySupplierCmd command);

    Long applySupplier(@Valid ApplyInquirySupplierCmd command);

    void auditSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId);

    void confirmInvitation(InquiryCode inquiryCode, SupplierCode supplierCode);

    void updateSupplier(@Valid UpdateInquirySupplierCmd command);

    void removeSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId);

    void updateQuoteProgress(QuoteSubmittedEvent quoteSubmittedEvent);
}
