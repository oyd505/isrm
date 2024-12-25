package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.port.repo.dao.ProductJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    private final ProductJpa productJpa;

    public ProductQueryService(ProductJpa productJpa) {
        this.productJpa = productJpa;
    }

    public ProductDO find(String productCode) {
        return productJpa.findById(productCode).orElse(new ProductDO());
    }

    public Page<ProductBasic> pageBasic(PageRequest pageable) {
        return productJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    public List<ProductCodeAndName> listCodeAndName() {
        return productJpa.listCodeAndName();
    }
} 