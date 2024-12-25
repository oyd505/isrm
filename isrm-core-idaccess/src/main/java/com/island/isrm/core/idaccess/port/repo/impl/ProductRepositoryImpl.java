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

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpa productJpa;
    private final ProductConverter productConverter;

    public ProductRepositoryImpl(ProductJpa productJpa, ProductConverter productConverter) {
        this.productJpa = productJpa;
        this.productConverter = productConverter;
    }

    @Override
    public Product find(ProductCode productCode) {
        Optional<ProductDO> productDO = productJpa.findById(productCode.getValue());
        return productConverter.toEntity(productDO.orElseThrow(
                () -> new DataNotFoundException(String.format("商品(%s)不存在", productCode.getValue()))
        ));
    }

    @Override
    public ProductCode add(Product product) {
        ProductDO productDO = productConverter.fromEntity(product);
        return new ProductCode(productJpa.save(productDO).getProductCode());
    }

    @Override
    public void update(Product product) {
        ProductDO productDO = productConverter.fromEntity(product);
        productJpa.save(productDO);
    }

    @Override
    public void remove(ProductCode productCode) {
        productJpa.deleteById(productCode.getValue());
    }
} 