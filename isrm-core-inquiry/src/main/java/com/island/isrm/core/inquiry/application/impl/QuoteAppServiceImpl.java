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
import com.island.isrm.core.inquiry.domain.message.QuoteEventPublisher;
import com.island.isrm.core.inquiry.domain.repo.QuoteRequestRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuoteAppServiceImpl implements QuoteAppService {
    private final QuoteAssembler quoteAssembler = QuoteAssembler.instance;
    private final QuoteRequestRepository quoteRequestRepository;
    private final QuoteCodeService quoteCodeService;
    private final QuoteInquiryService quoteInquiryService;
    @Resource
    private QuoteEventPublisher quoteEventPublisher;

    public QuoteAppServiceImpl(QuoteRequestRepository quoteRequestRepository,
                               QuoteCodeService quoteCodeService,
                               QuoteInquiryService quoteInquiryService) {
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteCodeService = quoteCodeService;
        this.quoteInquiryService = quoteInquiryService;
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
        quoteEventPublisher.publishQuoteSubmittedEvent(
                new QuoteSubmittedEvent(quoteCode, quoteRequest.getSupplier().getCode(), quoteRequest.getInquiryCode())
        );
    }

    @Transactional
    @Override
    public void updateItem(UpdateQuoteItemCmd command) {
        QuoteItem input = this.quoteAssembler.toUpdateEntity(command);
        QuoteRequest quoteRequest = this.quoteRequestRepository.findOneQuoteItem(
                input.getQuoteCode(), input.getQuoteItemId()
        );
        quoteRequest.updateQuoteItem(input);
        this.quoteRequestRepository.updateQuoteItem(quoteRequest);
    }
}
