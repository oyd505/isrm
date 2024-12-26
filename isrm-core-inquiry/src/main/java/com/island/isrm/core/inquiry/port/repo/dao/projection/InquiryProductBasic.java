/**
 * 询价商品基础信息接口
 * <p>
 * 该接口定义了获取询价商品基础信息的方法，包括商品的ID、询价编码、商品编码、名称、数量、单位、工厂名称和交货日期
 */
package com.island.isrm.core.inquiry.port.repo.dao.projection;

import java.math.BigDecimal;

/**
 * 询价商品基础信息接口
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface InquiryProductBasic {
    /**
     * 获取商品ID
     *
     * @return 商品ID
     */
    Long getId();

    /**
     * 获取询价编码
     *
     * @return 询价编码
     */
    String getInquiryCode();

    /**
     * 获取商品编码
     *
     * @return 商品编码
     */
    String getCode();

    /**
     * 获取商品名称
     *
     * @return 商品名称
     */
    String getName();

    /**
     * 获取商品数量
     *
     * @return 商品数量
     */
    BigDecimal getQuantity();

    /**
     * 获取商品单位
     *
     * @return 商品单位
     */
    String getUnits();

    /**
     * 获取工厂名称
     *
     * @return 工厂名称
     */
    String getFactoryName();

    /**
     * 获取交货日期
     *
     * @return 交货日期
     */
    String getDeliveryDate();
}
