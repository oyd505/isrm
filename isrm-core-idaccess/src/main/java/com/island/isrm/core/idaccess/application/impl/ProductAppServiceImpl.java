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

/**
 * 商品应用服务实现类，负责处理与商品相关的应用层逻辑
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Service
public class ProductAppServiceImpl implements ProductAppService {
    // 商品组装器，用于在领域模型和DTO之间进行转换
    private final ProductAssembler productAssembler = ProductAssembler.instance;
    // 商品仓储，用于对商品数据进行持久化操作
    private final ProductRepository productRepository;
    // 商品编码服务，用于生成唯一的商品编码
    private final ProductCodeService productCodeService;

    /**
     * 构造函数注入商品仓储和商品编码服务
     *
     * @param productRepository  商品仓储
     * @param productCodeService 商品编码服务
     */
    public ProductAppServiceImpl(ProductRepository productRepository, ProductCodeService productCodeService) {
        this.productRepository = productRepository;
        this.productCodeService = productCodeService;
    }

    /**
     * 创建新商品的应用服务方法
     *
     * @param command 创建商品的命令对象，包含商品信息
     * @return 新生成的商品编码
     */
    @Override
    public ProductCode create(CreateProductCmd command) {
        // 生成新的商品编码
        ProductCode productCode = productCodeService.generateProductCode();
        // 将命令对象和商品编码组装成商品实体
        Product product = productAssembler.toAddEntity(command, productCode);
        // 将新商品添加到仓储
        productRepository.add(product);
        return productCode;
    }

    /**
     * 更新现有商品的应用服务方法
     *
     * @param command 更新商品的命令对象，包含需要更新的商品信息
     */
    @Override
    public void update(UpdateProductCmd command) {
        // 将命令对象转换为商品实体，用于更新
        Product input = productAssembler.toUpdateEntity(command);
        // 从仓储中查找需要更新的商品
        Product product = productRepository.find(input.getProductCode());
        // 使用输入的商品实体更新找到的商品
        product.update(input);
        // 将更新后的商品保存到仓储
        productRepository.update(product);
    }

    /**
     * 删除商品的应用服务方法
     *
     * @param productCode 要删除的商品的编码
     */
    @Override
    public void remove(ProductCode productCode) {
        // 先从仓储中查找要删除的商品，以确保商品存在
        productRepository.find(productCode);
        // 从仓储中移除商品
        productRepository.remove(productCode);
    }
}
