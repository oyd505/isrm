package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 创建商品命令类
 * 用于处理创建新商品的请求
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class CreateProductCmd {
    @NotBlank
    private String name; // 商品名称，不能为空

    private BigDecimal price; // 商品价格

    private BigDecimal taxRate; // 税率

    private String unitName; // 商品单位名称

    private String unitSymbol; // 单位符号

    private String categoryCode; // 商品类别编码

    private String categoryName; // 商品类别名称
}
