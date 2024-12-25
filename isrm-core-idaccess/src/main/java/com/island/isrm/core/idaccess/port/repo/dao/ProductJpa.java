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

@Repository
public interface ProductJpa extends JpaRepository<ProductDO, String> {
    // 查询数据库中最大的商品编码
    @Query("SELECT MAX(p.productCode) FROM ProductDO p")
    String findMaxProductCode();

    @Query("SELECT p.productCode as productCode, p.name as name, p.price as price, " + 
    "p.taxRate as taxRate, p.unitName as unitName, p.unitSymbol as unitSymbol, " + 
    "p.categoryCode as categoryCode, p.categoryName as categoryName " + 
    "FROM ProductDO p ORDER BY p.lastModifiedDate DESC")
    Page<ProductBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    @Query("SELECT p.productCode as code, p.name as name FROM ProductDO p")
    List<ProductCodeAndName> listCodeAndName();
} 