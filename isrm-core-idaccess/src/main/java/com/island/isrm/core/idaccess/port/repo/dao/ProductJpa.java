package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品信息数据访问对象接口
 * 继承JpaRepository以获取基本的CRUD操作，并定义特定的查询方法
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public interface ProductJpa extends JpaRepository<ProductDO, String> {

    /**
     * 查询数据库中最大的商品编码
     * 用于生成新的商品编码
     *
     * @return 最大的商品编码字符串，如果数据库为空，则返回null
     */
    @Query("SELECT MAX(p.productCode) FROM ProductDO p")
    String findMaxProductCode();

    /**
     * 按最后修改日期降序查找商品基本信息
     * 用于获取按照最近修改时间排序的商品列表
     *
     * @param pageable 分页信息，包括页码和每页大小
     * @return 商品基本信息的分页列表
     */
    @Query("SELECT p.productCode as productCode, p.name as name, p.price as price, " +
            "p.taxRate as taxRate, p.unitName as unitName, p.unitSymbol as unitSymbol, " +
            "p.categoryCode as categoryCode, p.categoryName as categoryName " +
            "FROM ProductDO p ORDER BY p.lastModifiedDate DESC")
    Page<ProductBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    /**
     * 列出所有商品的编码和名称
     * 用于获取商品的简要信息列表，便于快速浏览或搜索
     *
     * @return 商品编码和名称的列表
     */
    @Query("SELECT p.productCode as code, p.name as name FROM ProductDO p")
    List<ProductCodeAndName> listCodeAndName();
} 
