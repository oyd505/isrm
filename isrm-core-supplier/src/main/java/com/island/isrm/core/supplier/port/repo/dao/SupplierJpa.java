package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierBasic;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SupplierJpa接口用于定义供应商数据访问对象（DAO）的方法
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public interface SupplierJpa extends ListCrudRepository<SupplierDO, String> {

    /**
     * 查找供应商编码的最大值
     * 这个方法主要用于生成新的供应商编码，确保其唯一性和顺序性
     *
     * @return 最大的供应商编码字符串
     */
    @Query("select max(s.supplierCode) from SupplierDO s")
    String findMaxSupplierCode();

    /**
     * 按最后修改日期降序查找供应商基本信息
     * 该方法主要用于获取供应商列表，按照最后修改日期从新到旧排序
     *
     * @param pageable 分页请求对象，包含分页参数如页码和每页大小
     * @return 包含供应商基本信息的分页对象
     */
    @Query("SELECT s.supplierCode as supplierCode, s.name as name, s.supplierStatus as supplierStatus" +
            " FROM SupplierDO s ORDER BY s.lastModifiedDate DESC ")
    Page<SupplierBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    /**
     * 列出所有供应商的编码和名称
     * 该方法主要用于获取所有供应商的简化信息，即编码和名称，通常用于列表展示或下拉菜单
     *
     * @return 包含供应商编码和名称的列表
     */
    @Query("SELECT s.supplierCode as code, s.name as name FROM SupplierDO s")
    List<SupplierCodeAndName> listCodeAndName();
}
