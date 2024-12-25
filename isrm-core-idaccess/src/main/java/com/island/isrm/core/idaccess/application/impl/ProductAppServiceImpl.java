package com.island.isrm.core.idaccess.application.impl;

import com.island.isrm.core.idaccess.application.ProductAppService;
import com.island.isrm.core.idaccess.application.ProductAssembler;
import com.island.isrm.core.idaccess.application.command.CreateProductCmd;
import com.island.isrm.core.idaccess.application.command.UpdateProductCmd;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.entity.Product;
import com.island.isrm.core.idaccess.domain.external.ProductCodeService;
import com.island.isrm.core.idaccess.domain.repo.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductAppServiceImpl implements ProductAppService {
    private final ProductAssembler productAssembler = ProductAssembler.instance;
    private final ProductRepository productRepository;
    private final ProductCodeService productCodeService;

    public ProductAppServiceImpl(ProductRepository productRepository, ProductCodeService productCodeService) {
        this.productRepository = productRepository;
        this.productCodeService = productCodeService;
    }

    @Override
    public ProductCode create(CreateProductCmd command) {
        ProductCode productCode = productCodeService.generateProductCode();
        Product product = productAssembler.toAddEntity(command, productCode);
        productRepository.add(product);
        return productCode;
    }

    @Override
    public void update(UpdateProductCmd command) {
        Product input = productAssembler.toUpdateEntity(command);
        Product product = productRepository.find(input.getProductCode());
        product.update(input);
        productRepository.update(product);
    }

    @Override
    public void remove(ProductCode productCode) {
        productRepository.find(productCode);
        productRepository.remove(productCode);
    }
} 