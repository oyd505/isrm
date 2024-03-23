package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Quantity;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.quote.DeliveryDeadline;
import com.island.isrm.core.inquiry.domain.dp.quote.InquiryItem;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 报价项
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class QuoteItem extends BaseEntity {
    // 报价项ID
    private final QuoteItemId quoteItemId;
    // 询价项
    private final InquiryItem inquiryItem;
    // 报价数量
    @Setter
    private Quantity quoteQuantity;
    // 交付期限
    @Setter
    private DeliveryDeadline deliveryDeadline;
    // 价格
    @Setter
    private Price price;
    // 报价编码
    private final QuoteCode quoteCode;

    public QuoteItem(QuoteItemId quoteItemId, InquiryItem inquiryItem, @NonNull QuoteCode quoteCode) {
        this.quoteItemId = quoteItemId;
        this.inquiryItem = inquiryItem;
        this.quoteCode = quoteCode;
    }

    /**
     * 更新报价项
     *
     * @param quoteItem 报价项
     */
    public void update(QuoteItem quoteItem) {
        this.quoteQuantity = quoteItem.getQuoteQuantity();
        this.price = quoteItem.getPrice();
        this.deliveryDeadline = quoteItem.getDeliveryDeadline();
    }

    /**
     * 计算报价金额
     *
     * @return 报价金额
     */
    public BigDecimal calculateAmount() {
        BigDecimal quantity = Optional.ofNullable(this.quoteQuantity).map(Quantity::getValue).orElseThrow(
                () -> new BizException("报价数量不能为空")
        );
        return Optional.ofNullable(this.price).map(Price::getValue).orElseThrow(
                () -> new BizException("报价价格不能为空")
        ).multiply(quantity);
    }
}
