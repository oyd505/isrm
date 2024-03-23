package com.island.isrm.core.inquiry.port.repo.dao.projection;

import java.math.BigDecimal;

public interface QuoteItemBasic {
    Long getId();

    String getProductCode();

    String getProductName();

    BigDecimal getPrice();

    BigDecimal getTaxRate();

    String getDeliveryDeadline();

    BigDecimal getQuoteQuantity();

    String getUnits();

    String getQuoteCode();
}
