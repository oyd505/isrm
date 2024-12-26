package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 添加询价商品命令类
 * 用于处理添加新询价商品的请求
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class AddInquiryProductCmd {
    /**
     * 商品编码，必须填写
     */
    @NotBlank
    private String code;

    /**
     * 商品名称，必须填写
     */
    @NotBlank
    private String name;

    /**
     * 商品数量，必须填写且不能为负数
     */
    @NotNull
    @DecimalMin("0")
    private BigDecimal quantity;

    /**
     * 计量单位，必须填写
     */
    @NotBlank
    private String units;

    /**
     * 工厂编码，可选
     */
    private String factoryCode;

    /**
     * 工厂名称，可选
     */
    private String factoryName;

    /**
     * 交货日期，必须填写且必须是未来日期
     */
    @NotNull
    @Future
    private LocalDate deliveryDate;
}
