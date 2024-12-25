package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.entity.Product;

/**
 * 商品仓库
 */
public interface ProductRepository {
    Product find(ProductCode productCode);

    ProductCode add(Product product);

    void update(Product product);

    void remove(ProductCode productCode);
} 