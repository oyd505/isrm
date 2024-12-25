package com.island.isrm.core.idaccess.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Unit;
import com.island.isrm.core.idaccess.domain.dp.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商品
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class Product extends BaseEntity {
    private final ProductCode productCode;
    @Setter
    private Name name;
    @Setter
    private Price price;
    @Setter
    private Unit unit;
    @Setter
    private Category category;

    public Product(ProductCode productCode) {
        this.productCode = productCode;
    }

    public void update(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.category = product.getCategory();
    }
} 