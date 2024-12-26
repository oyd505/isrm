package com.island.isrm.core.idaccess.domain.external;

import com.island.isrm.core.idaccess.domain.dp.ProductCode;

/**
 * 商品码服务接口
 * 提供生成商品码的方法，以确保每个商品的唯一标识
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface ProductCodeService {
    /**
     * 生成商品码
     *
     * @return ProductCode 生成的商品码实例
     */
    ProductCode generateProductCode();
}
