package com.island.isrm.core.inquiry.port.repo.dao.projection;

import java.math.BigDecimal;

/**
 * 报价单项目基本信息
 * 包括获取项目ID、商品编码、商品名称、价格、税率、交货期限、报价数量、单位和报价单编码
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface QuoteItemBasic {

    /**
     * 获取项目ID
     *
     * @return 项目ID
     */
    Long getId();

    /**
     * 获取商品编码
     *
     * @return 商品编码
     */
    String getProductCode();

    /**
     * 获取商品名称
     *
     * @return 商品名称
     */
    String getProductName();

    /**
     * 获取商品价格
     *
     * @return 商品价格
     */
    BigDecimal getPrice();

    /**
     * 获取税率
     *
     * @return 税率
     */
    BigDecimal getTaxRate();

    /**
     * 获取交货期限
     *
     * @return 交货期限
     */
    String getDeliveryDeadline();

    /**
     * 获取报价数量
     *
     * @return 报价数量
     */
    BigDecimal getQuoteQuantity();

    /**
     * 获取单位
     *
     * @return 单位
     */
    String getUnits();

    /**
     * 获取报价单编码
     *
     * @return 报价单编码
     */
    String getQuoteCode();
}
