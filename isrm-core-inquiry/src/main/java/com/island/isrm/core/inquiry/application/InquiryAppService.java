package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryProductId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquirySupplierId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 询价应用服务接口，定义了与询价相关的应用层操作。
 * 包括创建、更新、删除、提交、发布询价单，以及管理询价单中的商品和供应商的操作。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Validated
public interface InquiryAppService {

    /**
     * 创建新的询价单。
     *
     * @param command 创建询价单的请求命令，包含创建询价单所需的信息。
     * @return 新创建的询价单的编码。
     */
    InquiryCode create(@Valid CreateInquiryRequestCmd command);

    /**
     * 更新询价单信息。
     *
     * @param command 更新询价单的请求命令，包含需要更新的询价单信息。
     */
    void update(@Valid UpdateInquiryRequestCmd command);

    /**
     * 删除询价单。
     *
     * @param inquiryCode 要删除的询价单的编码。
     */
    void remove(InquiryCode inquiryCode);

    /**
     * 提交询价单。
     *
     * @param inquiryCode 要提交的询价单的编码。
     */
    void submit(InquiryCode inquiryCode);

    /**
     * 发布询价单。
     *
     * @param inquiryCode 要发布的询价单的编码。
     */
    void publish(InquiryCode inquiryCode);

    /**
     * 向询价单中添加商品。
     *
     * @param inquiryCode 询价单的编码。
     * @param command     添加商品的请求命令，包含要添加的商品信息。
     * @return 新增商品的ID。
     */
    Long addProduct(String inquiryCode, @Valid AddInquiryProductCmd command);

    /**
     * 更新询价单中的商品信息。
     *
     * @param inquiryCode 询价单的编码。
     * @param command     更新商品的请求命令，包含需要更新的商品信息。
     */
    void updateProduct(String inquiryCode, @Valid UpdateInquiryProductCmd command);

    /**
     * 从询价单中移除商品。
     *
     * @param inquiryCode      询价单的编码。
     * @param inquiryProductId 要移除的商品的ID。
     */
    void removeProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId);

    /**
     * 邀请供应商参与询价。
     *
     * @param inquiryCode 询价单的编码。
     * @param command     添加供应商的请求命令，包含要邀请的供应商信息。
     * @return 新增供应商的ID。
     */
    Long inviteSupplier(String inquiryCode, @Valid AddInquirySupplierCmd command);

    /**
     * 申请成为询价单的供应商。
     *
     * @param inquiryCode 询价单的编码。
     * @param command     申请供应商的请求命令，包含申请的供应商信息。
     * @return 询价供应商ID。
     */
    Long applySupplier(String inquiryCode, @Valid ApplyInquirySupplierCmd command);

    /**
     * 审核供应商的参与资格。
     *
     * @param inquiryCode       询价单的编码。
     * @param inquirySupplierId 供应商在询价单中的ID。
     */
    void auditSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId);

    /**
     * 确认供应商的邀请。
     *
     * @param inquiryCode  询价单的编码。
     * @param supplierCode 供应商的编码。
     */
    void confirmInvitation(InquiryCode inquiryCode, SupplierCode supplierCode);

    /**
     * 更新询价单中的供应商信息。
     *
     * @param inquiryCode 询价单的编码。
     * @param command     更新供应商的请求命令，包含需要更新的供应商信息。
     */
    void updateSupplier(String inquiryCode, @Valid UpdateInquirySupplierCmd command);

    /**
     * 从询价单中移除供应商。
     *
     * @param inquiryCode       询价单的编码。
     * @param inquirySupplierId 要移除的供应商在询价单中的ID。
     */
    void removeSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId);

    /**
     * 更新供应商的报价进度。
     *
     * @param quoteSubmittedEvent 报价提交事件，包含报价的相关信息。
     */
    void updateQuoteProgress(QuoteSubmittedEvent quoteSubmittedEvent);
}
