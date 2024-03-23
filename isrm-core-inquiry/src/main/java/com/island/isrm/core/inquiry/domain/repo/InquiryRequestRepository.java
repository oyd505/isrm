package com.island.isrm.core.inquiry.domain.repo;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryProductId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquirySupplierId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;

/**
 * 询价申请仓库
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public interface InquiryRequestRepository {
    /**
     * 根据询价编码查找询价申请
     *
     * @param inquiryCode 询价编码
     * @return 询价申请
     */
    InquiryRequest find(InquiryCode inquiryCode);

    /**
     * 根据询价编码查找询价申请及其关联的商品和供应商
     *
     * @param inquiryCode 询价编码
     * @return 询价申请
     */
    InquiryRequest findAll(InquiryCode inquiryCode);

    /**
     * 新增询价申请
     *
     * @param inquiryRequest 询价申请
     * @return 询价编码
     */
    InquiryCode add(InquiryRequest inquiryRequest);

    /**
     * 更新询价申请
     *
     * @param inquiryRequest 询价申请
     */
    void update(InquiryRequest inquiryRequest);

    /**
     * 更新询价申请及商品
     *
     * @param inquiryRequest 询价申请
     */
    void updateAndInquiryProduct(InquiryRequest inquiryRequest);

    /**
     * 移除询价申请及其关联商品和供应商
     *
     * @param inquiryCode 询价编码
     */
    void removeAll(InquiryCode inquiryCode);

    /**
     * 根据询价编码和商品ID查找商品
     *
     * @param inquiryCode      询价编码
     * @param inquiryProductId 询价商品ID
     * @return 询价申请带有一个询价商品
     */
    InquiryRequest findOneInquiryProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId);

    /**
     * 添加一个商品到询价申请
     *
     * @param inquiryRequest 询价申请
     * @return 询价商品ID
     **/
    InquiryProductId addOneInquiryProduct(InquiryRequest inquiryRequest);

    /**
     * 更新询价申请的商品
     *
     * @param inquiryRequest 询价申请
     */
    void updateInquiryProduct(InquiryRequest inquiryRequest);

    /**
     * 移除一个商品
     *
     * @param inquiryProductId 询价商品ID
     */
    void removeOneInquiryProduct(InquiryProductId inquiryProductId);

    /**
     * 根据询价编码和供应商ID查找供应商
     *
     * @param inquiryCode       询价编码
     * @param inquirySupplierId 询价供应商ID
     * @return 询价申请带有一个询价供应商
     */
    InquiryRequest findOneInquirySupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId);

    /**
     * 根据询价编码和供应商编码查找供应商
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 询价申请带有一个询价供应商
     */
    InquiryRequest findOneInquirySupplier(InquiryCode inquiryCode, SupplierCode supplierCode);

    /**
     * 添加一个供应商到询价申请
     *
     * @param inquiryRequest 询价申请
     * @return 询价供应商ID
     */
    InquirySupplierId addOneInquirySupplier(InquiryRequest inquiryRequest);

    /**
     * 更新询价申请的供应商
     *
     * @param inquiryRequest 询价申请
     */
    void updateInquirySupplier(InquiryRequest inquiryRequest);

    /**
     * 移除一个供应商
     *
     * @param inquirySupplierId 询价供应商ID
     */
    void removeOneInquirySupplier(InquirySupplierId inquirySupplierId);
}
