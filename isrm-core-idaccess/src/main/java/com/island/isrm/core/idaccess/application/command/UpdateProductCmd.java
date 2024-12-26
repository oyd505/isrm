package com.island.isrm.core.idaccess.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新商品信息的命令类
 * 继承自CreateProductCmd，以复用创建商品时的属性和逻辑
 * 主要新增了商品编码字段，用于标识和更新特定的商品
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductCmd extends CreateProductCmd {
    /**
     * 商品编码，用于唯一标识一个商品
     * 使用NotBlank注解确保在更新操作时该字段不能为空
     */
    @NotBlank
    private String productCode; // 商品编码
}
