package com.island.isrm.core.inquiry.port.repo.service;

import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemJpa;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteQueryService {
    private final QuoteRequestJpa quoteRequestJpa;
    private final QuoteItemJpa quoteItemJpa;

    public QuoteQueryService(QuoteRequestJpa quoteRequestJpa, QuoteItemJpa quoteItemJpa) {
        this.quoteRequestJpa = quoteRequestJpa;
        this.quoteItemJpa = quoteItemJpa;
    }

    public List<QuoteRequestBasic> findBasicByInquiryCodeAndSupplierCode(String inquiryCode, String supplierCode) {
        return this.quoteRequestJpa.findBasicByInquiryCodeAndSupplierCode(inquiryCode, supplierCode);
    }

    public QuoteRequestDO findById(String quoteCode) {
        return this.quoteRequestJpa.findById(quoteCode).orElse(new QuoteRequestDO());
    }

    public List<QuoteItemBasic> findItemBasicByQuoteCode(String quoteCode) {
        return this.quoteItemJpa.findItemBasicByQuoteCode(quoteCode);
    }

    public QuoteItemDO findByQuoteItemIdAndQuoteCode(Long quoteItemId, String quoteCode) {
        return this.quoteItemJpa.findByIdAndQuoteCode(quoteItemId, quoteCode).orElse(new QuoteItemDO());
    }
}
