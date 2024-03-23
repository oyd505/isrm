package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Amount;
import com.island.isrm.core.common.domain.dp.Currency;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.dp.inquiry.Supplier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 报价请求
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class QuoteRequest extends BaseEntity {
    // 报价编码
    private final QuoteCode quoteCode;
    // 供应商
    private final Supplier supplier;
    // 货币
    @Setter
    private Currency currency;
    // 金额
    @Setter
    private Amount amount;
    // 报价状态
    private QuoteStatus quoteStatus;
    // 询价编码
    private final InquiryCode inquiryCode;
    // 报价项目列表
    private final List<QuoteItem> quoteItemList = new ArrayList<>();

    public QuoteRequest(@NonNull QuoteCode quoteCode, Supplier supplier, @NonNull QuoteStatus quoteStatus, InquiryCode inquiryCode) {
        this.quoteCode = quoteCode;
        this.supplier = supplier;
        this.quoteStatus = quoteStatus;
        this.inquiryCode = inquiryCode;
    }

    /**
     * 检查是否可编辑
     */
    public void checkEditable() {
        if (!QuoteStatus.PENDING.equals(this.quoteStatus)) {
            throw new BizException(QuoteStatus.PENDING.name() + " 状态的报价才允许编辑操作");
        }
    }

    /**
     * 更新报价请求
     *
     * @param quoteRequest 新的报价请求
     */
    public void update(QuoteRequest quoteRequest) {
        this.checkEditable();
        this.currency = quoteRequest.getCurrency();
    }

    /**
     * 提交报价
     */
    public void submit() {
        this.checkEditable();
        if (CollectionUtils.isEmpty(quoteItemList)) {
            throw new BizException("报价明细项数量必须大于0");
        }
        BigDecimal sum = quoteItemList.stream().map(QuoteItem::calculateAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.amount = new Amount(sum);
        this.quoteStatus = QuoteStatus.SUBMITTED;
    }

    /**
     * 取消报价
     */
    public void cancel() {
        this.quoteStatus = QuoteStatus.CANCELED;
    }

    /**
     * 更新报价项目
     *
     * @param quoteItem 报价项目
     */
    public void updateQuoteItem(QuoteItem quoteItem) {
        this.checkEditable();
        this.getQuoteItemList().forEach(item -> item.update(quoteItem));
    }
}
