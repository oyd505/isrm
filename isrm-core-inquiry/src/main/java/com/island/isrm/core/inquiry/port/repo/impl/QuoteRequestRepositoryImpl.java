package com.island.isrm.core.inquiry.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.domain.repo.QuoteRequestRepository;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemConverter;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemJpa;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestConverter;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuoteRequestRepositoryImpl implements QuoteRequestRepository {
    private final QuoteRequestJpa quoteRequestJpa;
    private final QuoteRequestConverter quoteRequestConverter;

    private final QuoteItemJpa quoteItemJpa;
    private final QuoteItemConverter quoteItemConverter;

    public QuoteRequestRepositoryImpl(QuoteRequestJpa quoteRequestJpa, QuoteRequestConverter quoteRequestConverter,
                                      QuoteItemJpa quoteItemJpa, QuoteItemConverter quoteItemConverter) {
        this.quoteRequestJpa = quoteRequestJpa;
        this.quoteRequestConverter = quoteRequestConverter;
        this.quoteItemJpa = quoteItemJpa;
        this.quoteItemConverter = quoteItemConverter;
    }

    @Override
    public QuoteRequest find(QuoteCode quoteCode) {
        Optional<QuoteRequestDO> quoteRequestDO = this.quoteRequestJpa.findById(quoteCode.getValue());
        return this.quoteRequestConverter.toEntity(quoteRequestDO.orElseThrow(
                () -> new DataNotFoundException(String.format("报价申请(%s)不存在", quoteCode.getValue()))
        ));
    }

    @Override
    public QuoteRequest findAll(QuoteCode quoteCode) {
        QuoteRequest quoteRequest = find(quoteCode);
        List<QuoteItemDO> quoteItemDOList = this.quoteItemJpa.findByQuoteCode(quoteCode.getValue());
        quoteItemDOList.forEach(quoteItemDO -> quoteRequest.getQuoteItemList().add(
                this.quoteItemConverter.toEntity(quoteItemDO)
        ));
        return quoteRequest;
    }

    @Override
    public Optional<QuoteRequest> findOnlyPending(InquiryCode inquiryCode, SupplierCode supplierCode) {
        List<QuoteRequestDO> quoteRequestDOList = this.quoteRequestJpa.findByInquiryCodeAndSupplierCodeAndStatus(
                inquiryCode.getValue(), supplierCode.getValue(), QuoteStatus.PENDING.name());
        return quoteRequestDOList.stream().findFirst().map(this.quoteRequestConverter::toEntity);
    }

    @Override
    public Optional<QuoteRequest> findOnlySubmitted(InquiryCode inquiryCode, SupplierCode supplierCode) {
        List<QuoteRequestDO> quoteRequestDOList = this.quoteRequestJpa.findByInquiryCodeAndSupplierCodeAndStatus(
                inquiryCode.getValue(), supplierCode.getValue(), QuoteStatus.SUBMITTED.name());
        return quoteRequestDOList.stream().findFirst().map(this.quoteRequestConverter::toEntity);
    }

    @Override
    public QuoteCode addAll(QuoteRequest quoteRequest) {
        List<QuoteItemDO> quoteItemDOList = quoteRequest.getQuoteItemList().stream()
                .map(this.quoteItemConverter::fromEntity).toList();
        this.quoteItemJpa.saveAll(quoteItemDOList);
        QuoteRequestDO quoteRequestDO = this.quoteRequestConverter.fromEntity(quoteRequest);
        return new QuoteCode(this.quoteRequestJpa.save(quoteRequestDO).getQuoteCode());
    }

    @Override
    public void update(QuoteRequest quoteRequest) {
        QuoteRequestDO quoteRequestDO = this.quoteRequestConverter.fromEntity(quoteRequest);
        this.quoteRequestJpa.save(quoteRequestDO);
    }

    @Override
    public void removeAll(QuoteCode quoteCode) {
        this.quoteRequestJpa.deleteById(quoteCode.getValue());
        this.quoteItemJpa.deleteByQuoteCode(quoteCode.getValue());
    }

    @Override
    public QuoteRequest findOneQuoteItem(QuoteCode quoteCode, QuoteItemId quoteItemId) {
        QuoteRequest quoteRequest = find(quoteCode);
        Optional<QuoteItemDO> quoteItemDO = this.quoteItemJpa.findByIdAndQuoteCode(quoteItemId.getId(), quoteCode.getValue());
        QuoteItem quoteItem = this.quoteItemConverter.toEntity(quoteItemDO.orElseThrow(
                () -> new DataNotFoundException(String.format("报价申请(%s)明细项(%d)不存在", quoteCode.getValue(), quoteItemId.getId()))
        ));
        quoteRequest.getQuoteItemList().add(quoteItem);
        return quoteRequest;
    }

    @Override
    public void updateQuoteItem(QuoteRequest quoteRequest) {
        List<QuoteItemDO> quoteItemDOList = quoteRequest.getQuoteItemList().stream()
                .map(this.quoteItemConverter::fromEntity).toList();
        this.quoteItemJpa.saveAll(quoteItemDOList);
    }
}
