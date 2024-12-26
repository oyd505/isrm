package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.idaccess.application.command.CreateProductCmd;
import com.island.isrm.core.idaccess.application.command.UpdateProductCmd;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 商品应用服务接口
 * 提供商品相关的应用层服务，包括创建、更新和删除商品
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
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
