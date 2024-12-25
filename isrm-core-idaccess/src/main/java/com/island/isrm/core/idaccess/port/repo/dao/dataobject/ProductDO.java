package com.island.isrm.core.idaccess.port.repo.dao.dataobject;

import java.math.BigDecimal;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class ProductDO extends BaseDataObject {
    @Id
    @Column(nullable = false, length = 32)
    private String productCode; // 商品编码
    private String name; // 商品名称
    private BigDecimal price; // 商品价格
    private BigDecimal taxRate;
    private String unitName; // 商品单位
    private String unitSymbol;
    private String categoryCode; // 商品类别
    private String categoryName;
}