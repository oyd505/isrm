package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Unit;
import com.island.isrm.core.idaccess.application.command.CreateProductCmd;
import com.island.isrm.core.idaccess.application.command.UpdateProductCmd;
import com.island.isrm.core.idaccess.domain.dp.Category;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.entity.Product;

public class ProductAssembler {
    public final static ProductAssembler instance = new ProductAssembler();

    /**
     * 将创建商品命令转换为商品实体对象
     *
     * @param command 创建商品命令对象
     * @param productCode 商品编码
     * @return 转换后的商品实体对象
     */
    public Product toAddEntity(CreateProductCmd command, ProductCode productCode) {
        Product product = new Product(productCode);
        assemble(command, product);
        return product;
    }

    /**
     * 将更新商品命令转换为商品实体对象
     *
     * @param command 更新商品命令对象
     * @return 转换后的商品实体对象
     */
    public Product toUpdateEntity(UpdateProductCmd command) {
        Product product = new Product(new ProductCode(command.getProductCode()));
        assemble(command, product);
        return product;
    }

    /**
     * 将命令的属性复制到商品实体对象中
     *
     * @param source 创建或更新商品命令对象
     * @param target 商品实体对象
     */
    private void assemble(CreateProductCmd source, Product target) {
        target.setName(new Name(source.getName()));
        target.setPrice(new Price(source.getPrice(), source.getTaxRate()));
        target.setUnit(new Unit(source.getUnitName(), source.getUnitSymbol()));
        target.setCategory(new Category(source.getCategoryCode(), source.getCategoryName()));
    }
} 