package com.island.isrm.core.idaccess.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.entity.Product;
import com.island.isrm.core.idaccess.domain.repo.ProductRepository;
import com.island.isrm.core.idaccess.port.repo.dao.ProductConverter;
import com.island.isrm.core.idaccess.port.repo.dao.ProductJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 商品仓储实现类，提供商品数据访问的实现
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // 用于执行商品信息的JPA操作
    private final ProductJpa productJpa;
    // 用于在商品实体和数据对象之间进行转换
    private final ProductConverter productConverter;

    /**
     * 构造函数，初始化ProductJpa和ProductConverter
     *
     * @param productJpa       商品信息的JPA操作接口
     * @param productConverter 商品实体和数据对象之间的转换器
     */
    public ProductRepositoryImpl(ProductJpa productJpa, ProductConverter productConverter) {
        this.productJpa = productJpa;
        this.productConverter = productConverter;
    }

    /**
     * 根据商品编码查找商品信息
     *
     * @param productCode 商品编码
     * @return 对应的商品实体
     * @throws DataNotFoundException 如果找不到对应的商品信息
     */
    @Override
    public Product find(ProductCode productCode) {
        Optional<ProductDO> productDO = productJpa.findById(productCode.getValue());
        return productConverter.toEntity(productDO.orElseThrow(
                () -> new DataNotFoundException(String.format("商品(%s)不存在", productCode.getValue()))
        ));
    }

    /**
     * 向数据库中添加新的商品信息
     *
     * @param product 要添加的商品实体
     * @return 添加后的商品编码
     */
    @Override
    public ProductCode add(Product product) {
        ProductDO productDO = productConverter.fromEntity(product);
        return new ProductCode(productJpa.save(productDO).getProductCode());
    }

    /**
     * 更新数据库中的商品信息
     *
     * @param product 要更新的商品实体
     */
    @Override
    public void update(Product product) {
        ProductDO productDO = productConverter.fromEntity(product);
        productJpa.save(productDO);
    }

    /**
     * 根据商品编码从数据库中删除商品信息
     *
     * @param productCode 要删除的商品的编码
     */
    @Override
    public void remove(ProductCode productCode) {
        productJpa.deleteById(productCode.getValue());
    }
}
