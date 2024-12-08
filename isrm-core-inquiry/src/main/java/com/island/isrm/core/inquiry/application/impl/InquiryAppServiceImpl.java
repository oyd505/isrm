package com.island.isrm.core.inquiry.application.impl;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.application.InquiryAssembler;
import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import com.island.isrm.core.inquiry.domain.external.InquiryCodeService;
import com.island.isrm.core.inquiry.domain.repo.InquiryRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InquiryAppServiceImpl implements InquiryAppService {
    private final InquiryAssembler inquiryAssembler = InquiryAssembler.instance;
    private final InquiryRequestRepository inquiryRequestRepository;
    private final InquiryCodeService inquiryCodeService;

    public InquiryAppServiceImpl(InquiryRequestRepository inquiryRequestRepository,
                                 InquiryCodeService inquiryCodeService) {
        this.inquiryRequestRepository = inquiryRequestRepository;
        this.inquiryCodeService = inquiryCodeService;
    }

    @Transactional
    @Override
    public InquiryCode create(CreateInquiryRequestCmd command) {
        InquiryCode inquiryCode = this.inquiryCodeService.generateSerialNumber();
        InquiryRequest inquiryRequest = this.inquiryAssembler.toAddEntity(command, inquiryCode, InquiryStatus.PENDING);
        return this.inquiryRequestRepository.add(inquiryRequest);
    }

    @Transactional
    @Override
    public void update(UpdateInquiryRequestCmd command) {
        InquiryRequest input = this.inquiryAssembler.toUpdateEntity(command, InquiryStatus.PENDING);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(input.getInquiryCode());
        inquiryRequest.update(input);
        this.inquiryRequestRepository.update(inquiryRequest);
    }

    @Transactional
    @Override
    public void remove(InquiryCode inquiryCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(inquiryCode);
        inquiryRequest.checkEditable();
        this.inquiryRequestRepository.removeAll(inquiryCode);
    }

    @Transactional
    @Override
    public void submit(InquiryCode inquiryCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findAll(inquiryCode);
        inquiryRequest.submit();
        this.inquiryRequestRepository.updateAndInquiryProduct(inquiryRequest);
    }

    @Transactional
    @Override
    public void publish(InquiryCode inquiryCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findAll(inquiryCode);
        inquiryRequest.publish();
        this.inquiryRequestRepository.update(inquiryRequest);
    }

    @Transactional
    @Override
    public Long addProduct(String inquiryCode, AddInquiryProductCmd command) {
        InquiryProduct inquiryProduct = this.inquiryAssembler.toAddEntity(inquiryCode, command);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(inquiryProduct.getInquiryCode());
        inquiryRequest.addProduct(inquiryProduct);
        return inquiryRequestRepository.addOneInquiryProduct(inquiryRequest).getId();
    }

    @Transactional
    @Override
    public void updateProduct(String inquiryCode, UpdateInquiryProductCmd command) {
        InquiryProduct input = this.inquiryAssembler.toUpdateEntity(inquiryCode, command);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquiryProduct(
                input.getInquiryCode(), input.getInquiryProductId()
        );
        inquiryRequest.updateProduct(input);
        this.inquiryRequestRepository.updateInquiryProduct(inquiryRequest);
    }

    @Transactional
    @Override
    public void removeProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquiryProduct(
                inquiryCode, inquiryProductId
        );
        inquiryRequest.checkEditable();
        this.inquiryRequestRepository.removeOneInquiryProduct(inquiryProductId);
    }

    @Transactional
    @Override
    public Long inviteSupplier(String inquiryCode, AddInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = this.inquiryAssembler.toAddEntity(inquiryCode, command);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquirySupplier.getInquiryCode(), inquirySupplier.getSupplier().getCode());
        inquiryRequest.inviteSupplier(inquirySupplier);
        return this.inquiryRequestRepository.addOneInquirySupplier(inquiryRequest).getId();
    }

    @Transactional
    @Override
    public Long applySupplier(String inquiryCode, ApplyInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = this.inquiryAssembler.toApplyEntity(inquiryCode, command);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquirySupplier.getInquiryCode(), inquirySupplier.getSupplier().getCode()
        );
        inquiryRequest.applySupplier(inquirySupplier);
        return this.inquiryRequestRepository.addOneInquirySupplier(inquiryRequest).getId();
    }

    @Transactional
    @Override
    public void auditSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquiryCode, inquirySupplierId
        );
        inquiryRequest.auditSupplier(inquirySupplierId);
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    @Transactional
    @Override
    public void confirmInvitation(InquiryCode inquiryCode, SupplierCode supplierCode) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(inquiryCode, supplierCode);
        inquiryRequest.confirmInvitation(supplierCode);
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    @Transactional
    @Override
    public void updateSupplier(String inquiryCode, UpdateInquirySupplierCmd command) {
        InquirySupplier input = this.inquiryAssembler.toUpdateEntity(inquiryCode, command);
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                input.getInquiryCode(), input.getInquirySupplierId()
        );
        inquiryRequest.updateSupplier(input);
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    @Transactional
    @Override
    public void removeSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId) {
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquiryCode, inquirySupplierId
        );
        inquiryRequest.checkEditable();
        this.inquiryRequestRepository.removeOneInquirySupplier(inquirySupplierId);
    }

    @Transactional
    @Override
    public void updateQuoteProgress(QuoteSubmittedEvent quoteSubmittedEvent) {
        SupplierCode supplierCode = new SupplierCode(quoteSubmittedEvent.getSupplierCode());
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                new InquiryCode(quoteSubmittedEvent.getInquiryCode()), supplierCode
        );
        inquiryRequest.updateQuoteProgress(supplierCode, new QuoteCode(quoteSubmittedEvent.getQuoteCode()));
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }
}
