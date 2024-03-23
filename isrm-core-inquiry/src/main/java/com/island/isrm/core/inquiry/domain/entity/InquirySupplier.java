package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Flag;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 询价供应商
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class InquirySupplier extends BaseEntity {
    // 询价供应商ID
    private final InquirySupplierId inquirySupplierId;
    // 供应商
    @Setter
    private Supplier supplier;
    // 联系人
    @Setter
    private Contact contact;
    // 询价编码
    private final InquiryCode inquiryCode;
    // 确认状态
    @Setter
    private Flag confirmed;
    // 审核状态
    @Setter
    private Flag audited;
    // 报价进度
    @Setter
    private QuoteProgress quoteProgress;

    public InquirySupplier(InquirySupplierId inquirySupplierId, InquiryCode inquiryCode) {
        this.inquirySupplierId = inquirySupplierId;
        this.inquiryCode = inquiryCode;
    }

    /**
     * 供应商确认/响应询价
     */
    public void confirm() {
        this.confirmed = Flag.Y;
    }

    /**
     * 审核询价供应商
     */
    public void audit() {
        this.audited = Flag.Y;
    }

    /**
     * 更新询价供应商信息
     */
    public void update(InquirySupplier inquirySupplier) {
        this.supplier = inquirySupplier.getSupplier();
        this.contact = inquirySupplier.getContact();
        this.audited = inquirySupplier.getAudited();
    }

    /**
     * 检查询价供应商是否允许报价
     */
    public void checkQuoteEnabled() {
        if (!this.confirmed.isOk()) {
            throw new BizException("已确认邀请的供应商才允许报价");
        }
        if (!this.audited.isOk()) {
            throw new BizException("已审核的供应商才允许报价");
        }
    }

    /**
     * 更新报价进度
     *
     * @param quoteCode 报价编码
     */
    public void updateQuoteProgress(QuoteCode quoteCode) {
        if (this.quoteProgress == null) {
            this.quoteProgress = new QuoteProgress(1, quoteCode.getValue());
        } else {
            this.quoteProgress = this.quoteProgress.createNextProgress(quoteCode.getValue());
        }
    }
}
