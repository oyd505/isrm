package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.application.ProductAppService;
import com.island.isrm.core.idaccess.application.command.CreateProductCmd;
import com.island.isrm.core.idaccess.application.command.UpdateProductCmd;
import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductCodeAndName;
import com.island.isrm.core.idaccess.port.repo.service.ProductQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductRest类提供了商品的RESTful API接口
 * 它允许客户端对商品数据进行查询、创建、更新和删除操作
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@RestController
@RequestMapping("/v1/product")
public class ProductRest {
    private final ProductAppService productAppService;
    private final ProductQueryService productQueryService;

    public ProductRest(ProductAppService productAppService, ProductQueryService productQueryService) {
        this.productAppService = productAppService;
        this.productQueryService = productQueryService;
    }

    /**
     * 分页查询商品基本信息
     * 此方法用于获取商品的分页基本信息，包括商品名称、编码等
     *
     * @param pageNumber 页码，从0开始
     * @param pageSize   每页的大小
     * @return 返回包含商品基本信息的分页对象
     */
    @GetMapping("/page/basic")
    public Page<ProductBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return productQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 查询所有商品的编码和名称列表
     * 此方法用于获取所有商品的编码和名称，常用于下拉列表等需要简要信息的场景
     *
     * @return 返回包含所有商品编码和名称的列表
     */
    @GetMapping("/list/code/name")
    public List<ProductCodeAndName> listCodeAndName() {
        return productQueryService.listCodeAndName();
    }

    /**
     * 根据商品编码查询商品详细信息
     * 此方法用于根据商品的唯一编码查询商品的详细信息
     *
     * @param productCode 商品的唯一编码
     * @return 返回商品的详细信息对象
     */
    @GetMapping("/{productCode}")
    public ProductDO find(@PathVariable String productCode) {
        return productQueryService.find(productCode);
    }

    /**
     * 创建新商品
     * 此方法用于创建一个新的商品，需要提供商品的详细信息
     *
     * @param command 创建商品的命令对象，包含新商品所需的所有信息
     * @return 返回新创建商品的编码
     */
    @PostMapping("/create")
    public String createProduct(@RequestBody CreateProductCmd command) {
        return productAppService.create(command).getValue();
    }

    /**
     * 更新商品信息
     * 此方法用于更新一个已存在商品的信息，需要提供商品的编码和需要更新的信息
     *
     * @param command 更新商品的命令对象，包含需要更新的商品信息
     */
    @PostMapping("/update")
    public void updateProduct(@RequestBody UpdateProductCmd command) {
        productAppService.update(command);
    }

    /**
     * 删除商品
     * 此方法用于根据商品的编码删除一个商品
     *
     * @param productCode 需要删除的商品的编码
     */
    @PostMapping("/remove/{productCode}")
    public void removeProduct(@PathVariable String productCode) {
        productAppService.remove(new ProductCode(productCode));
    }
}
