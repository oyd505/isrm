package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierQuote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InquirySupplierJpa extends ListCrudRepository<InquirySupplierDO, Long> {
    Optional<InquirySupplierDO> findByIdAndInquiryCode(Long id, String inquiryCode);

    Optional<InquirySupplierDO> findByInquiryCodeAndCode(String inquiryCode, String code);

    @Query("SELECT is.id as id, is.inquiryCode as inquiryCode, is.code as code, is.name as name, is.contactName as contactName," +
            " is.contactPhone as contactPhone, is.audited as audited, is.confirmed as confirmed" +
            " FROM InquirySupplierDO is WHERE is.inquiryCode = ?1")
    List<InquirySupplierBasic> findSupplierBasicByInquiryCode(String inquiryCode);

    @Query("SELECT is.id as id, is.quoteCount as quoteCount, is.quoteCode as quoteCode, is.name as name, is.contactName as contactName," +
            " is.audited as audited, is.confirmed as confirmed" +
            " FROM InquirySupplierDO is WHERE is.inquiryCode = ?1")
    List<InquirySupplierQuote> findSupplierQuoteByInquiryCode(String inquiryCode);

    List<InquirySupplierDO> findByInquiryCode(String inquiryCode);

    void deleteByInquiryCode(String inquiryCode);
}
