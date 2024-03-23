package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRequestJpa extends ListCrudRepository<QuoteRequestDO, String> {
    List<QuoteRequestDO> findByInquiryCodeAndSupplierCodeAndStatus(String inquiryCode, String supplierCode, String status);

    @Query("SELECT qr.quoteCode as quoteCode, qr.supplierName as supplierName, qr.status as status, qr.inquiryCode as inquiryCode" +
            " FROM QuoteRequestDO qr WHERE qr.inquiryCode = ?1 AND qr.supplierCode = ?2")
    List<QuoteRequestBasic> findBasicByInquiryCodeAndSupplierCode(String inquiryCode, String supplierCode);
}
