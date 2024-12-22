package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Currency;
import com.island.isrm.core.common.domain.dp.LineNumber;
import com.island.isrm.core.common.domain.dp.Reason;
import com.island.isrm.core.common.domain.dp.Title;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 询价申请
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class InquiryRequest extends BaseEntity {
    // 询价单编码
    private final InquiryCode inquiryCode;
    // 询价标题
    @Setter
    private Title title;
    // 询价原因
    @Setter
    private Reason reason;
    // 参与方式
    @Setter
    private Participation participation;
    // 采购员
    @Setter
    private Buyer buyer;
    // 询价状态
    private InquiryStatus inquiryStatus;
    // 报价时间区间
    @Setter
    private QuoteTime quoteTime;
    // 付款条款
    @Setter
    private PaymentTerms paymentTerms;
    // 询价货币
    @Setter
    private Currency currency;
    // 采购组织
    @Setter
    private PurchasingOrg purchasingOrg;
    // 询价商品列表
    private final List<InquiryProduct> inquiryProducts = new ArrayList<>();
    // 询价供应商列表
    private final List<InquirySupplier> inquirySuppliers = new ArrayList<>();

    public InquiryRequest(@NonNull InquiryCode inquiryCode, @NonNull InquiryStatus inquiryStatus) {
        this.inquiryCode = inquiryCode;
        this.inquiryStatus = inquiryStatus;
    }

    /**
     * 判断询价状态是否可编辑，不可编辑则抛出异常
     */
    public void checkEditable() {
        if (!this.inquiryStatus.isEditable()) {
            throw new BizException(InquiryStatus.PENDING.name() + " 状态的询价才允许编辑操作");
        }
    }

    /**
     * 更新询价请求信息
     *
     * @param inquiryRequest 询价请求对象
     */
    public void update(InquiryRequest inquiryRequest) {
        this.checkEditable();
        this.title = inquiryRequest.getTitle();
        this.participation = inquiryRequest.getParticipation();
        this.buyer = inquiryRequest.getBuyer();
        this.reason = inquiryRequest.getReason();
        this.quoteTime = inquiryRequest.getQuoteTime();
        this.paymentTerms = inquiryRequest.getPaymentTerms();
        this.currency = inquiryRequest.getCurrency();
        this.purchasingOrg = inquiryRequest.getPurchasingOrg();
    }

    /**
     * 提交询价单
     */
    public void submit() {
        if (!InquiryStatus.PENDING.equals(this.inquiryStatus)) {
            throw new BizException(InquiryStatus.PENDING.name() + " 状态的询价才允许提交");
        }
        if (Objects.isNull(this.quoteTime)) {
            throw new BizException("报价时间不能为空");
        }
        if (CollectionUtils.isEmpty(inquiryProducts)) {
            throw new BizException("询价商品数量必须大于0");
        }
        if (this.isOpen()) {
            if (!CollectionUtils.isEmpty(inquirySuppliers)) {
                throw new BizException("公开模式不需要邀请供应商");
            }
        } else {
            if (CollectionUtils.isEmpty(inquirySuppliers)) {
                throw new BizException("邀请模式必须邀请供应商");
            }
        }
        for (int i = 0; i < inquiryProducts.size(); i++) {
            inquiryProducts.get(i).setLineNumber(new LineNumber(i + 1));
        }
        this.inquiryStatus = InquiryStatus.SUBMITTED;
    }

    /**
     * 检查询价参与方式是否开放
     *
     * @return 若开放返回true，否则返回false
     */
    private boolean isOpen() {
        if (Objects.isNull(this.participation)) {
            throw new BizException("询价参与方式不能为空");
        }
        return this.participation.isOpen();
    }

    /**
     * 发布询价
     */
    public void publish() {
        if (!InquiryStatus.SUBMITTED.equals(this.inquiryStatus)) {
            throw new BizException(InquiryStatus.SUBMITTED.name() + " 状态的询价才允许发布");
        }
//        if (CollectionUtils.isEmpty(inquirySuppliers)) {
//            throw new BizException("参与供应商数量必须大于0");
//        }
//        long canQuoteCount = inquirySuppliers.stream().
//                filter(supplier -> supplier.getConfirmed().isOk() && supplier.getAudited().isOk()).count();
//        if (canQuoteCount < 1) {
//            throw new BizException("能报价的供应商数量必须大于0");
//        }
        this.inquiryStatus = InquiryStatus.PUBLISHED;
    }

    /**
     * 向询价单中添加商品
     *
     * @param inquiryProduct 询价商品
     */
    public void addProduct(InquiryProduct inquiryProduct) {
        this.checkEditable();
        // 同一商品允许重复添加
        this.inquiryProducts.add(inquiryProduct);
    }

    /**
     * 更新询价单中商品信息
     *
     * @param inquiryProduct 询价商品
     */
    public void updateProduct(InquiryProduct inquiryProduct) {
        this.checkEditable();
        Optional<InquiryProduct> inquiryProductOptional = this.inquiryProducts.stream()
                .filter(product -> product.getInquiryProductId().equals(inquiryProduct.getInquiryProductId()))
                .findFirst();
        inquiryProductOptional.ifPresent(product -> product.update(inquiryProduct));
    }

    /**
     * 邀请供应商参加询价
     *
     * @param inquirySupplier 要邀请的供应商
     */
    public void inviteSupplier(InquirySupplier inquirySupplier) {
        // checkInviteEnabled
        this.checkEditable();
        if (this.isOpen()) {
            throw new BizException(Participation.INVITED.name() + " 参与方式的询价才允许邀请供应商参加");
        }
        this.addOrUpdateSupplier(inquirySupplier);
    }

    /**
     * 添加或更新供应商信息
     *
     * @param inquirySupplier 询价供应商对象
     */
    private void addOrUpdateSupplier(InquirySupplier inquirySupplier) {
        if (this.inquirySuppliers.isEmpty()) {
            this.inquirySuppliers.add(inquirySupplier);
        } else {
            // 如果已邀请，则更新邀请信息
            Optional<InquirySupplier> inquirySupplierOptional = this.inquirySuppliers.stream()
                    .filter(iSupplier -> iSupplier.getSupplier().isMatched(inquirySupplier.getSupplier().getCode()))
                    .findFirst();
            inquirySupplierOptional.ifPresentOrElse(
                    existingSupplier -> existingSupplier.update(inquirySupplier),
                    () -> this.inquirySuppliers.add(inquirySupplier)
            );
        }
    }

    /**
     * 供应商申请参加询价
     *
     * @param inquirySupplier 询价供应商
     */
    public void applySupplier(InquirySupplier inquirySupplier) {
        // checkApplyEnabled
        if (!InquiryStatus.PUBLISHED.equals(this.inquiryStatus)) {
            throw new BizException(InquiryStatus.PUBLISHED.name() + " 状态的询价才允许供应商申请参加");
        }
        if (!this.isOpen()) {
            throw new BizException(Participation.OPEN.name() + " 参与方式的询价才允许供应商申请参加");
        }
        if (LocalDateTime.now().isAfter(this.quoteTime.getEndTime())) {
            throw new BizException("报价时间已过，无法申请参加");
        }
        inquirySupplier.confirm();
        this.addOrUpdateSupplier(inquirySupplier);
    }

    /**
     * 更新询价供应商信息
     *
     * @param inquirySupplier 询价供应商对象
     */
    public void updateSupplier(InquirySupplier inquirySupplier) {
        this.checkEditable();
        Optional<InquirySupplier> inquirySupplierOptional = this.inquirySuppliers.stream()
                .filter(iSupplier -> iSupplier.getInquirySupplierId().equals(inquirySupplier.getInquirySupplierId()))
                .findFirst();
        inquirySupplierOptional.ifPresent(supplier -> supplier.update(inquirySupplier));
    }

    /**
     * 审计询价供应商
     *
     * @param inquirySupplierId inquirySupplierId
     */
    public void auditSupplier(InquirySupplierId inquirySupplierId) {
        this.checkResponseEnabled();
        Optional<InquirySupplier> inquirySupplierOptional = this.inquirySuppliers.stream()
                .filter(inquirySupplier -> inquirySupplier.getInquirySupplierId().equals(inquirySupplierId))
                .findFirst();
        inquirySupplierOptional.ifPresent(InquirySupplier::audit);
    }

    /**
     * 检查响应是否已启用
     */
    private void checkResponseEnabled() {
        if (!InquiryStatus.PUBLISHED.equals(this.inquiryStatus)) {
            throw new BizException(InquiryStatus.PUBLISHED.name() + " 状态的询价才允许审核/确认操作");
        }
        if (LocalDateTime.now().isAfter(this.quoteTime.getEndTime())) {
            throw new BizException("报价时间已过，无法审核/确认");
        }
    }

    /**
     * 确认邀请
     *
     * @param supplierCode 供应商编码
     */
    public void confirmInvitation(SupplierCode supplierCode) {
        this.checkResponseEnabled();
        Optional<InquirySupplier> inquirySupplierOptional = this.inquirySuppliers.stream()
                .filter(inquirySupplier -> inquirySupplier.getSupplier().isMatched(supplierCode)).findFirst();
        inquirySupplierOptional.ifPresent(InquirySupplier::confirm);
    }

    /**
     * 检查是否可报价
     *
     * @param supplierCode 供应商编码
     * @return 供应商
     */
    public InquirySupplier checkQuoteEnabled(SupplierCode supplierCode) {
        if (!InquiryStatus.PUBLISHED.equals(this.inquiryStatus)) {
            throw new BizException(InquiryStatus.PUBLISHED.name() + " 状态的询价才允许报价");
        }
        if (this.quoteTime.getStartTime() == null || LocalDateTime.now().isBefore(this.quoteTime.getStartTime())) {
            throw new BizException("报价时间未到，无法报价");
        }
        if (this.quoteTime.getEndTime() == null || LocalDateTime.now().isAfter(this.quoteTime.getEndTime())) {
            throw new BizException("报价时间已过，无法报价");
        }
        Optional<InquirySupplier> inquirySupplierOptional = this.inquirySuppliers.stream()
                .filter(inquirySupplier -> inquirySupplier.getSupplier().isMatched(supplierCode)).findFirst();
        InquirySupplier inquirySupplier = inquirySupplierOptional.orElseThrow(() -> new BizException("供应商没有参与此次询价"));
        inquirySupplier.checkQuoteEnabled();
        return inquirySupplier;
    }

    /**
     * 更新报价进度
     *
     * @param supplierCode 供应商编码
     * @param quoteCode    报价编码
     */
    public void updateQuoteProgress(SupplierCode supplierCode, QuoteCode quoteCode) {
        InquirySupplier inquirySupplier = this.checkQuoteEnabled(supplierCode);
        inquirySupplier.updateQuoteProgress(quoteCode);
    }

}
