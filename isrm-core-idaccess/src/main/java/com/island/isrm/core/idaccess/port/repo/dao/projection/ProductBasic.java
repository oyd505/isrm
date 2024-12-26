package com.island.isrm.core.idaccess.port.repo.dao.projection;

import java.math.BigDecimal;

/**
 * 商品基础信息接口
 * 该接口定义了获取商品基本属性的方法，用于在不同的上下文中以标准方式访问商品信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface ProductBasic {
    /**
     * 获取商品编码
     * 商品编码是商品的唯一标识，用于在系统中唯一确定一个商品
     *
     * @return 商品编码
     */
    String getProductCode();

    /**
     * 获取商品名称
     * 商品名称是商品的中文名称，用于在界面上显示商品信息
     *
     * @return 商品名称
     */
    String getName();

    /**
     * 获取商品价格
     * 商品价格是指商品的销售价格，用于计算商品的销售金额
     *
     * @return 商品价格
     */
    BigDecimal getPrice();

    /**
     * 获取税率
     * 税率是指商品销售时应缴纳的税款比例，用于计算商品的含税价格
     *
     * @return 税率
     */
    BigDecimal getTaxRate();

    /**
     * 获取商品单位名称
     * 商品单位名称是指商品的计量单位的全称，用于在界面上显示商品的计量单位信息
     *
     * @return 商品单位名称
     */
    String getUnitName();

    /**
     * 获取单位符号
     * 单位符号是指商品的计量单位的简称或符号，用于在界面上简洁显示商品的计量单位信息
     *
     * @return 单位符号
     */
    String getUnitSymbol();

    /**
     * 获取商品类别编码
     * 商品类别编码是商品所属类别的编码，用于对商品进行分类管理
     *
     * @return 商品类别编码
     */
    String getCategoryCode();

    /**
     * 获取商品类别名称
     * 商品类别名称是商品所属类别的名称，用于在界面上显示商品类别信息
     *
     * @return 商品类别名称
     */
    String getCategoryName();
}
