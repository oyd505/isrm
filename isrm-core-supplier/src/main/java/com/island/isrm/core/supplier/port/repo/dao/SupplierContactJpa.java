package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.ContactNameAndPhone;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierContactBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * SupplierContactJpa接口用于处理供应商联系人信息的数据访问层操作
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public interface SupplierContactJpa extends ListCrudRepository<SupplierContactDO, Long> {

    /**
     * 根据ID和供应商编码查找供应商联系人信息
     *
     * @param id           供应商联系人的唯一标识符
     * @param supplierCode 供应商的编码
     * @return 包含供应商联系人信息的Optional对象，如果找不到则返回空Optional
     */
    Optional<SupplierContactDO> findByIdAndSupplierCode(Long id, String supplierCode);

    /**
     * 根据供应商编码查询供应商联系人基本信息
     * 此查询返回一个包含供应商联系人基本信息（ID、姓名、电话和供应商编码）的列表
     *
     * @param supplierCode 供应商的编码
     * @return 一个包含SupplierContactBasic对象的列表，每个对象包含联系人的基本信息
     */
    @Query("SELECT sc.id as id, sc.name as name, sc.phone as phone, sc.supplierCode as supplierCode " +
            " FROM SupplierContactDO sc WHERE sc.supplierCode = ?1")
    List<SupplierContactBasic> findContactBasicBySupplierCode(String supplierCode);

    /**
     * 根据供应商编码查询供应商联系人的姓名和电话列表
     * 此查询返回一个包含联系人姓名和电话的列表，用于快速查看联系人信息
     *
     * @param supplierCode 供应商的编码
     * @return 一个包含ContactNameAndPhone对象的列表，每个对象包含联系人的姓名和电话
     */
    @Query("SELECT sc.name as name, sc.phone as phone " +
            " FROM SupplierContactDO sc WHERE sc.supplierCode = ?1")
    List<ContactNameAndPhone> listContactNameAndPhone(String supplierCode);

    /**
     * 根据供应商编码删除所有相关的供应商联系人信息
     * 此方法用于清理特定供应商的所有联系人信息，通常在供应商信息发生重大变更时使用
     *
     * @param supplierCode 供应商的编码
     */
    void deleteBySupplierCode(String supplierCode);
}
