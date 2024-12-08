package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryRequestBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRequestJpa extends ListCrudRepository<InquiryRequestDO, String> {

    @Query("SELECT ir.inquiryCode as inquiryCode, ir.title as title, ir.buyerName as buyerName, ir.status as status" +
            " FROM InquiryRequestDO ir ORDER BY ir.lastModifiedDate DESC")
    Page<InquiryRequestBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    @Query("SELECT ir.inquiryCode as inquiryCode, ir.title as title, ir.buyerName as buyerName, ir.status as status" +
            " FROM InquiryRequestDO ir WHERE ir.status != 'PENDING'" +
            " AND (ir.participation = 'OPEN' OR (ir.participation != 'OPEN' AND EXISTS (SELECT s.inquiryCode FROM InquirySupplierDO s WHERE s.inquiryCode = ir.id AND s.code = ?1)))" +
            " ORDER BY ir.lastModifiedDate DESC")
    List<InquiryRequestBasic> findBasicForQuoteOrderByLastModifiedDateDesc(String supplierCode);
}
