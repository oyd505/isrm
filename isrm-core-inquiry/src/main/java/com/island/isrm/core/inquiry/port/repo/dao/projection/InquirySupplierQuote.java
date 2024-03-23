package com.island.isrm.core.inquiry.port.repo.dao.projection;

public interface InquirySupplierQuote {
    Long getId();

    String getName();

    String getContactName();

    String getAudited();

    String getConfirmed();

    Integer getQuoteCount();

    String getQuoteCode();
}
