package com.island.isrm.core.idaccess.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * ProductDO类代表商品信息的数据对象，继承自BaseDataObject，用于在数据库中存储和操作商品信息。
 * 该类利用Lombok注解简化了getter、setter以及equals和hashcode的实现，并使用JPA注解来映射数据库表结构。
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class ProductDO extends BaseDataObject {
    /**
     * 商品编码，作为主键，长度为32，不可为空。
     * 用于唯一标识数据库中的每一个商品。
     */
    @Id
    @Column(nullable = false, length = 32)
    private String productCode;
    /**
     * 商品名称，用于描述商品的名称。
     */
    private String name;
    /**
     * 商品价格，使用BigDecimal来精确表示商品的价格。
     */
    private BigDecimal price;
    /**
     * 税率，用于表示商品的税收比例。
     */
    private BigDecimal taxRate;
    /**
     * 商品单位的名称，如“个”、“件”等，用于描述商品的计量单位。
     */
    private String unitName;
    /**
     * 商品单位的符号，可能用于表示商品单位的简写或符号表示。
     */
    private String unitSymbol;
    /**
     * 商品类别编码，用于将商品归类到不同的类别中。
     */
    private String categoryCode;
    /**
     * 商品类别名称，与类别编码对应，用于更直观地显示商品类别信息。
     */
    private String categoryName;
}
