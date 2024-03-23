package com.island.isrm.core.inquiry.port.repo.service;

import com.island.isrm.core.inquiry.port.repo.dao.InquiryProductJpa;
import com.island.isrm.core.inquiry.port.repo.dao.InquiryRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.InquirySupplierJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryProductBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryRequestBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierQuote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryQueryService {
    private final InquiryRequestJpa inquiryRequestJpa;
    private final InquiryProductJpa inquiryProductJpa;
    private final InquirySupplierJpa inquirySupplierJpa;

    public InquiryQueryService(InquiryRequestJpa inquiryRequestJpa,
                               InquiryProductJpa inquiryProductJpa,
                               InquirySupplierJpa inquirySupplierJpa) {
        this.inquiryRequestJpa = inquiryRequestJpa;
        this.inquiryProductJpa = inquiryProductJpa;
        this.inquirySupplierJpa = inquirySupplierJpa;
    }

    public List<InquiryRequestBasic> findBasicOrderByLastModifiedDateDesc() {
        return this.inquiryRequestJpa.findBasicOrderByLastModifiedDateDesc();
    }

    public List<InquiryRequestBasic> findBasicForQuoteOrderByLastModifiedDateDesc(String supplierCode) {
        return this.inquiryRequestJpa.findBasicForQuoteOrderByLastModifiedDateDesc(supplierCode);
    }

    public InquiryRequestDO findById(String inquiryCode) {
        return this.inquiryRequestJpa.findById(inquiryCode).orElse(new InquiryRequestDO());
    }

    public List<InquiryProductBasic> findProductBasicByInquiryCode(String inquiryCode) {
        return this.inquiryProductJpa.findProductBasicByInquiryCode(inquiryCode);
    }

    public InquiryProductDO findByInquiryProductIdAndInquiryCode(Long inquiryProductId, String inquiryCode) {
        return this.inquiryProductJpa.findByIdAndInquiryCode(inquiryProductId, inquiryCode).orElse(new InquiryProductDO());
    }

    public List<InquirySupplierBasic> findSupplierBasicByInquiryCode(String inquiryCode) {
        return this.inquirySupplierJpa.findSupplierBasicByInquiryCode(inquiryCode);
    }

    public List<InquirySupplierQuote> findSupplierQuoteByInquiryCode(String inquiryCode) {
        return this.inquirySupplierJpa.findSupplierQuoteByInquiryCode(inquiryCode);
    }

    public InquirySupplierDO findByInquirySupplierIdAndInquiryCode(Long inquirySupplierId, String inquiryCode) {
        return this.inquirySupplierJpa.findByIdAndInquiryCode(inquirySupplierId, inquiryCode).orElse(new InquirySupplierDO());
    }

    public InquirySupplierDO findByInquiryCodeAndCode(String inquiryCode, String supplierCode) {
        return this.inquirySupplierJpa.findByInquiryCodeAndCode(inquiryCode, supplierCode).orElse(new InquirySupplierDO());
    }

}
