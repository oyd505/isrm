package com.island.isrm.core.idaccess.application;

import org.springframework.validation.annotation.Validated;

import com.island.isrm.core.idaccess.application.command.CreateProductCmd;
import com.island.isrm.core.idaccess.application.command.UpdateProductCmd;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;

import jakarta.validation.Valid;

@Validated
public interface ProductAppService {

    /**
     * 创建商品
     *
     * @param command 创建商品命令对象
     * @return 新增商品编码
     */
    ProductCode create(@Valid CreateProductCmd command);

    /**
     * 更新商品
     *
     * @param command 更新商品命令对象
     */
    void update(@Valid UpdateProductCmd command);

    /**
     * 删除商品
     *
     * @param productCode 商品编码
     */
    void remove(ProductCode productCode);
} 