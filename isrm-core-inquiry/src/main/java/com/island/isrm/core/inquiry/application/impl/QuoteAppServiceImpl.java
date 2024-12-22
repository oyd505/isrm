package com.island.isrm.core.inquiry.application.impl;

import com.island.isrm.core.inquiry.application.QuoteAppService;
import com.island.isrm.core.inquiry.application.QuoteAssembler;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import com.island.isrm.core.inquiry.domain.external.QuoteCodeService;
import com.island.isrm.core.inquiry.domain.external.QuoteInquiryService;
import com.island.isrm.core.inquiry.domain.repo.QuoteRequestRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuoteAppServiceImpl implements QuoteAppService {
    private final QuoteAssembler quoteAssembler = QuoteAssembler.instance;
    private final QuoteRequestRepository quoteRequestRepository;
    private final QuoteCodeService quoteCodeService;
    private final QuoteInquiryService quoteInquiryService;
    private final ApplicationEventPublisher publisher;

    public QuoteAppServiceImpl(QuoteRequestRepository quoteRequestRepository,
                               QuoteCodeService quoteCodeService,
                               QuoteInquiryService quoteInquiryService, ApplicationEventPublisher publisher) {
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteCodeService = quoteCodeService;
        this.quoteInquiryService = quoteInquiryService;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public QuoteCode create(InquiryCode inquiryCode, SupplierCode supplierCode) {
        // 如果已经有未提交状态的报价单，不创建新报价单，返回未提交状态的报价单的报价编号
        Optional<QuoteRequest> pendingQuoteRequest = this.quoteRequestRepository.findOnlyPending(inquiryCode, supplierCode);
        return pendingQuoteRequest.map(QuoteRequest::getQuoteCode).orElseGet(() -> {
            QuoteCode quoteCode = this.quoteCodeService.generateSerialNumber();
            QuoteRequest quoteRequest = this.quoteInquiryService.createQuote(inquiryCode, supplierCode, quoteCode);
            return this.quoteRequestRepository.addAll(quoteRequest);
        });
    }

    @Transactional
    @Override
    public void update(UpdateQuoteRequestCmd command) {
        QuoteRequest input = this.quoteAssembler.toUpdateEntity(command, QuoteStatus.PENDING);
        QuoteRequest quoteRequest = this.quoteRequestRepository.find(input.getQuoteCode());
        quoteRequest.update(input);
        this.quoteRequestRepository.update(quoteRequest);
    }

    @Transactional
    @Override
    public void remove(QuoteCode quoteCode) {
        QuoteRequest quoteRequest = this.quoteRequestRepository.find(quoteCode);
        quoteRequest.checkEditable();
        this.quoteRequestRepository.removeAll(quoteCode);
    }

    @Transactional
    @Override
    public void submit(QuoteCode quoteCode) {
        QuoteRequest quoteRequest = this.quoteRequestRepository.findAll(quoteCode);
        this.quoteInquiryService.checkQuoteEnabled(quoteRequest.getInquiryCode(), quoteRequest.getSupplier().getCode());
        quoteRequest.submit();
        // 失效老版本,确保只有一个生效报价
        Optional<QuoteRequest> submittedQuoteRequest = this.quoteRequestRepository.findOnlySubmitted(
                quoteRequest.getInquiryCode(), quoteRequest.getSupplier().getCode());
        submittedQuoteRequest.ifPresent(quote -> {
            quote.cancel();
            this.quoteRequestRepository.update(quote);
        });
        this.quoteRequestRepository.update(quoteRequest);
        // 发送报价已提交的事件
        this.publisher.publishEvent(new QuoteSubmittedEvent(this, quoteCode.getValue(),
                quoteRequest.getSupplier().getCode().getValue(), quoteRequest.getInquiryCode().getValue()));
    }

    @Transactional
    @Override
    public void updateItem(String quoteCode, UpdateQuoteItemCmd command) {
        QuoteItem input = this.quoteAssembler.toUpdateEntity(quoteCode, command);
        QuoteRequest quoteRequest = this.quoteRequestRepository.findOneQuoteItem(
                input.getQuoteCode(), input.getQuoteItemId()
        );
        quoteRequest.updateQuoteItem(input);
        this.quoteRequestRepository.updateQuoteItem(quoteRequest);
    }
}
