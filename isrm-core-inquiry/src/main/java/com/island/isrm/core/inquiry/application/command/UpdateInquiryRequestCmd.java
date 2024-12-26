package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新询价请求的命令类
 * 继承自CreateInquiryRequestCmd，用于处理询价请求的更新操作
 * 添加了inquiryCode字段，用于标识询价请求的唯一编码
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquiryRequestCmd extends CreateInquiryRequestCmd {
    /**
     * 询价请求的唯一编码，用于标识和更新特定的询价请求
     * 使用@NotBlank注解确保编码不为空，以保证数据的完整性
     */
    @NotBlank
    private String inquiryCode;
}
