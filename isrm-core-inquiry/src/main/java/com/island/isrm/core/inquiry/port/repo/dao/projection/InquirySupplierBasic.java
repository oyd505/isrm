package com.island.isrm.core.inquiry.port.repo.dao.projection;

public interface InquirySupplierBasic {
    Long getId();

    String getInquiryCode();

    String getCode();

    String getName();

    String getContactName();

    String getContactPhone();

    String getAudited();

    String getConfirmed();
}
