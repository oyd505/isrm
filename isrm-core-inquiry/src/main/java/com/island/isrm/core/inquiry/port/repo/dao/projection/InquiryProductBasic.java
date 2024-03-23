package com.island.isrm.core.inquiry.port.repo.dao.projection;

import java.math.BigDecimal;

public interface InquiryProductBasic {
    Long getId();

    String getInquiryCode();

    String getCode();

    String getName();

    BigDecimal getQuantity();

    String getUnits();

    String getFactoryName();

    String getDeliveryDate();
}
