package com.island.isrm.core.idaccess.port.repo.dao.projection;

import java.math.BigDecimal;

public interface ProductBasic {
    String getProductCode(); // 商品编码

    String getName(); // 商品名称

    BigDecimal getPrice(); // 商品价格

    BigDecimal getTaxRate(); // 税率

    String getUnitName(); // 商品单位

    String getUnitSymbol(); // 单位符号

    String getCategoryCode(); // 商品类别编码

    String getCategoryName(); // 商品类别名称
} 