package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 更新报价单条目的命令类
 * 用于封装更新报价单条目时所需的各种参数
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class UpdateQuoteItemCmd {

    /**
     * 报价单条目的唯一标识符
     * 用于指定需要更新的条目
     */
    @NotNull
    @Positive
    private Long id;

    /**
     * 报价的数量
     * 必须为正数，表示有效的报价数量
     */
    @NotNull
    @Min(value = 1)
    private BigDecimal quoteQuantity;

    /**
     * 计量单位
     * 用于描述报价商品的计量方式
     */
    @NotBlank
    private String units;

    /**
     * 交货截止日期
     * 用于指定报价商品的预期交货时间
     */
    @NotBlank
    private LocalDate deliveryDeadline;

    /**
     * 商品的单价
     * 必须为正数，表示有效的商品价格
     */
    @NotNull
    @Positive
    private BigDecimal price;

    /**
     * 税率
     * 用于指定商品价格的税率
     */
    @NotNull
    @Positive
    private BigDecimal taxRate;
}