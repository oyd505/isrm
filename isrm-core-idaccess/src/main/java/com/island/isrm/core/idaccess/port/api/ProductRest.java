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

@RestController
@RequestMapping("/v1/product")
public class ProductRest {
    private final ProductAppService productAppService;
    private final ProductQueryService productQueryService;

    public ProductRest(ProductAppService productAppService, ProductQueryService productQueryService) {
        this.productAppService = productAppService;
        this.productQueryService = productQueryService;
    }

    @GetMapping("/page/basic")
    public Page<ProductBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return productQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/list/code/name")
    public List<ProductCodeAndName> listCodeAndName() {
        return productQueryService.listCodeAndName();
    }

    @GetMapping("/{productCode}")
    public ProductDO find(@PathVariable String productCode) {
        return productQueryService.find(productCode);
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody CreateProductCmd command) {
        return productAppService.create(command).getValue();
    }

    @PostMapping("/update")
    public void updateProduct(@RequestBody UpdateProductCmd command) {
        productAppService.update(command);
    }

    @PostMapping("/remove/{productCode}")
    public void removeProduct(@PathVariable String productCode) {
        productAppService.remove(new ProductCode(productCode));
    }
}
