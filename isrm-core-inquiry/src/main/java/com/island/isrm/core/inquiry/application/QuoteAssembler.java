package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.common.domain.dp.Currency;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Quantity;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.quote.DeliveryDeadline;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;

public class QuoteAssembler {
    public final static QuoteAssembler instance = new QuoteAssembler();

    public QuoteRequest toUpdateEntity(UpdateQuoteRequestCmd command, QuoteStatus quoteStatus) {
        QuoteRequest update = new QuoteRequest(new QuoteCode(command.getQuoteCode()), null, quoteStatus, null);
        update.setCurrency(new Currency(command.getCurrency()));
        return update;
    }

    public QuoteItem toUpdateEntity(UpdateQuoteItemCmd command) {
        QuoteItem update = new QuoteItem(new QuoteItemId(command.getId()), null, new QuoteCode(command.getQuoteCode()));
        update.setQuoteQuantity(new Quantity(command.getQuoteQuantity(), command.getUnits()));
        update.setPrice(new Price(command.getPrice(), command.getTaxRate()));
        update.setDeliveryDeadline(new DeliveryDeadline(command.getDeliveryDeadline()));
        return update;
    }
}
