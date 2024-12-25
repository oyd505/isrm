package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateProductCmd {
    @NotBlank
    private String name; // 商品名称
    private BigDecimal price; // 商品价格
    private BigDecimal taxRate; // 税率
    private String unitName; // 商品单位
    private String unitSymbol; // 单位符号
    private String categoryCode; // 商品类别
    private String categoryName; // 商品类别名称
} 