package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新询价商品命令类
 * 继承自 AddInquiryProductCmd 以复用添加询价商品时的属性和逻辑
 * 主要新增了 id 属性，用于标识需要更新的询价商品
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquiryProductCmd extends AddInquiryProductCmd {
    /**
     * 询价商品的唯一标识
     * 使用 @NotNull 和 @Positive 注解确保 id 值存在且为正数
     */
    @NotNull
    @Positive
    private Long id;
}
