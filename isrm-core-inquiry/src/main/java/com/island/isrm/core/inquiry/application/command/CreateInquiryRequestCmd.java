package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建询价请求的命令对象
 * 用于封装创建询价时所需的各种参数
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class CreateInquiryRequestCmd {
    /**
     * 询价标题，不能为空
     */
    @NotBlank
    private String title;

    /**
     * 询价原因，可选字段
     */
    private String reason;

    /**
     * 参与方式，不能为空
     */
    @NotBlank
    private String participation;

    /**
     * 买家编码，不能为空
     */
    @NotBlank
    private String buyerCode;

    /**
     * 买家名称，可选字段
     */
    private String buyerName;

    /**
     * 询价开始时间，可选字段
     */
    private LocalDateTime startTime;

    /**
     * 询价结束时间，可选字段
     */
    private LocalDateTime endTime;

    /**
     * 付款条款，可选字段
     */
    private String paymentTerms;

    /**
     * 货币种类，可选字段
     */
    private String currency;

    /**
     * 采购组织编码，可选字段
     */
    private String purOrgCode;

    /**
     * 采购组织名称，可选字段
     */
    private String purOrgName;
}
