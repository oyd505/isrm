package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Quantity;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Slf4j
public class QuoteItemTest {

    @Test
    public void calculateAmount() {
        QuoteItem quoteItem = new QuoteItem(null, null, new QuoteCode("XXX"));
        assertThrows(BizException.class, quoteItem::calculateAmount);
        quoteItem.setQuoteQuantity(new Quantity(new BigDecimal("10001"), "个"));
        assertThrows(BizException.class, quoteItem::calculateAmount);
        quoteItem.setPrice(new Price(new BigDecimal("1.23"), new BigDecimal("6")));
        assertEquals(new BigDecimal("12301.23"), quoteItem.calculateAmount());
    }
}
