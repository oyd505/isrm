package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.entity.Product;

/**
 * 商品仓储接口
 * 定义了对商品实体进行基本操作的方法，包括查找、添加、更新和删除
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface ProductRepository {
    /**
     * 根据商品编码查找商品
     *
     * @param productCode 商品编码
     * @return 返回找到的商品实体，如果找不到则返回null
     */
    Product find(ProductCode productCode);

    /**
     * 向商品仓储中添加新的商品
     *
     * @param product 要添加的商品实体
     * @return 返回添加后的商品编码
     */
    ProductCode add(Product product);

    /**
     * 更新商品仓储中的商品信息
     *
     * @param product 要更新的商品实体
     */
    void update(Product product);

    /**
     * 从商品仓储中删除商品
     *
     * @param productCode 要删除的商品的编码
     */
    void remove(ProductCode productCode);
}
