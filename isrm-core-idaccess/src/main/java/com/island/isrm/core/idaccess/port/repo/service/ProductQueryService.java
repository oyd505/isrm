package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.port.repo.dao.ProductJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductQueryService 类提供商品相关的查询服务
 * 它使用 ProductJpa 来访问数据库中的商品信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class ProductQueryService {

    // ProductJpa 是用于访问商品数据的 JPA仓储接口
    private final ProductJpa productJpa;

    /**
     * 构造函数注入 ProductJpa
     *
     * @param productJpa 用于访问商品数据的 JPA仓储接口
     */
    public ProductQueryService(ProductJpa productJpa) {
        this.productJpa = productJpa;
    }

    /**
     * 根据商品编码查找商品详细信息
     * 如果找不到匹配的商品，则返回一个新的空 ProductDO 对象
     *
     * @param productCode 商品编码，用于查找特定的商品
     * @return 匹配的商品信息，如果找不到则返回空的 ProductDO 对象
     */
    public ProductDO find(String productCode) {
        return productJpa.findById(productCode).orElse(new ProductDO());
    }

    /**
     * 分页查询商品的基本信息服务
     * 结果按照最后修改日期降序排列
     *
     * @param pageable 分页请求对象，包含分页参数
     * @return 包含商品基本信息服务的分页对象
     */
    public Page<ProductBasic> pageBasic(PageRequest pageable) {
        return productJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    /**
     * 查询所有商品的编码和名称列表
     * 这个方法用于获取一个包含所有商品编码和名称的列表
     *
     * @return 商品编码和名称的列表
     */
    public List<ProductCodeAndName> listCodeAndName() {
        return productJpa.listCodeAndName();
    }
}
