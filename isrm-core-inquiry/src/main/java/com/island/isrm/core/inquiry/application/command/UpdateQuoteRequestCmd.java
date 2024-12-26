package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 更新报价请求命令类
 * 用于处理更新报价的请求，确保报价编码不为空，并允许更新货币信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class UpdateQuoteRequestCmd {
    /**
     * 报价编码，不能为空
     * 用于标识一个唯一的报价请求
     */
    @NotBlank
    private String quoteCode;

    /**
     * 货币编码
     * 用于指定报价请求中的货币类型
     */
    private String currency;
}
