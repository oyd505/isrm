package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.LineNumber;
import com.island.isrm.core.common.domain.dp.Quantity;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 询价商品
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class InquiryProduct extends BaseEntity {
    // 询价商品ID
    private final InquiryProductId inquiryProductId;
    // 商品
    @Setter
    private Product product;
    // 询价数量
    @Setter
    private Quantity quantity;
    // 工厂组织
    @Setter
    private FactoryOrg factoryOrg;
    // 交货日期
    @Setter
    private DeliveryDate deliveryDate;
    // 询价编码
    private final InquiryCode inquiryCode;
    // 行号
    @Setter
    private LineNumber lineNumber;

    public InquiryProduct(InquiryProductId inquiryProductId, InquiryCode inquiryCode) {
        this.inquiryProductId = inquiryProductId;
        this.inquiryCode = inquiryCode;
    }

    /**
     * 更新询价商品
     *
     * @param inquiryProduct 询价商品
     */
    public void update(InquiryProduct inquiryProduct) {
        this.product = inquiryProduct.getProduct();
        this.quantity = inquiryProduct.getQuantity();
        this.factoryOrg = inquiryProduct.getFactoryOrg();
        this.deliveryDate = inquiryProduct.getDeliveryDate();
    }

}
