package com.island.isrm.core.supplier.port.repo.service;

import com.island.isrm.core.supplier.port.repo.dao.SupplierContactJpa;
import com.island.isrm.core.supplier.port.repo.dao.SupplierJpa;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.ContactNameAndPhone;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierBasic;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierCodeAndName;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierContactBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商查询服务类，提供与供应商相关的查询操作
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class SupplierQueryService {
    // 供应商数据访问对象
    private final SupplierJpa supplierJpa;
    // 供应商联系人数据访问对象
    private final SupplierContactJpa supplierContactJpa;

    /**
     * 构造函数，初始化供应商查询服务类
     *
     * @param supplierJpa        供应商数据访问对象
     * @param supplierContactJpa 供应商联系人数据访问对象
     */
    public SupplierQueryService(SupplierJpa supplierJpa, SupplierContactJpa supplierContactJpa) {
        this.supplierJpa = supplierJpa;
        this.supplierContactJpa = supplierContactJpa;
    }

    /**
     * 分页查询供应商基本信息
     *
     * @param pageable 分页参数
     * @return 供应商基本信息的分页结果
     */
    public Page<SupplierBasic> pageBasic(Pageable pageable) {
        return this.supplierJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    /**
     * 查询所有供应商的编码和名称
     *
     * @return 供应商编码和名称的列表
     */
    public List<SupplierCodeAndName> listCodeAndName() {
        return this.supplierJpa.listCodeAndName();
    }

    /**
     * 根据供应商编码查找供应商详细信息
     *
     * @param supplierCode 供应商编码
     * @return 供应商详细信息对象，如果找不到则返回空的供应商对象
     */
    public SupplierDO find(String supplierCode) {
        return this.supplierJpa.findById(supplierCode).orElse(new SupplierDO());
    }

    /**
     * 根据供应商编码查询供应商联系人基本信息
     *
     * @param supplierCode 供应商编码
     * @return 供应商联系人基本信息列表
     */
    public List<SupplierContactBasic> listContactBasic(String supplierCode) {
        return this.supplierContactJpa.findContactBasicBySupplierCode(supplierCode);
    }

    /**
     * 根据供应商编码查询供应商联系人的姓名和电话
     *
     * @param supplierCode 供应商编码
     * @return 联系人姓名和电话列表
     */
    public List<ContactNameAndPhone> listContactNameAndPhone(String supplierCode) {
        return this.supplierContactJpa.listContactNameAndPhone(supplierCode);
    }

    /**
     * 根据联系人ID和供应商编码查找供应商联系人详细信息
     *
     * @param id           联系人ID
     * @param supplierCode 供应商编码
     * @return 供应商联系人详细信息对象，如果找不到则返回空的联系人对象
     */
    public SupplierContactDO findContact(Long id, String supplierCode) {
        return this.supplierContactJpa.findByIdAndSupplierCode(id, supplierCode).orElse(new SupplierContactDO());
    }
}
