package com.island.isrm.core.idaccess.domain.entity;

import com.island.isrm.core.common.domain.BaseEntity;
import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Unit;
import com.island.isrm.core.idaccess.domain.dp.Category;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商品领域实体类，继承自BaseEntity，表示商品的基本信息
 * 使用Lombok注解来简化equalsAndHashCode、getter和setter的编写，并启用链式调用
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(chain = true)
public class Product extends BaseEntity {
    // 商品编码，最终属性，一旦设置不可更改
    private final ProductCode productCode;
    // 商品名称，可设置和获取
    @Setter
    private Name name;
    // 商品价格，可设置和获取
    @Setter
    private Price price;
    // 商品单位，可设置和获取
    @Setter
    private Unit unit;
    // 商品类别，可设置和获取
    @Setter
    private Category category;

    /**
     * 构造方法，传入商品编码初始化商品对象
     *
     * @param productCode 商品编码
     */
    public Product(ProductCode productCode) {
        this.productCode = productCode;
    }

    /**
     * 更新商品信息
     * 通过传入一个新的Product对象，将其属性值复制到当前对象，实现信息更新
     *
     * @param product 新的商品对象，包含待更新的信息
     */
    public void update(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.category = product.getCategory();
    }
}
