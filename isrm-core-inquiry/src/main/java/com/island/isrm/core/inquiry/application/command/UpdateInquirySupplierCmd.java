package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新询价供应商命令类
 * 继承自 ApplyInquirySupplierCmd，用于处理更新供应商信息的请求
 * 主要用途是封装更新操作的数据，确保数据的完整性、一致性和有效性
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquirySupplierCmd extends ApplyInquirySupplierCmd {
    /**
     * 供应商ID
     * 使用 @NotNull 和 @Positive 注解确保 ID 不为空且为正数
     * 这是为了保证数据库查询和业务逻辑的正确性
     */
    @NotNull
    @Positive
    private Long id;

    /**
     * 审核状态
     * 用于表示供应商的审核状态，例如：未审核、审核中、已审核等
     * 这个字段的存在是为了在业务流程中跟踪和控制供应商的审核状态
     */
    private String audited;
}
