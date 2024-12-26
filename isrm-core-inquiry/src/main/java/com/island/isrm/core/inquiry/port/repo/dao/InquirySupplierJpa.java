package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierQuote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * InquirySupplierJpa接口用于处理供应商查询相关的数据访问操作。
 * 它继承自ListCrudRepository，从而获得对InquirySupplierDO实体进行基本CRUD操作的能力。
 * 此外，它还定义了一些特定于业务需求的查询方法。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Repository
public interface InquirySupplierJpa extends ListCrudRepository<InquirySupplierDO, Long> {

    /**
     * 根据ID和询价编码查找供应商实体。
     *
     * @param id          供应商ID
     * @param inquiryCode 询价编码
     * @return 包含指定ID和询价编码的供应商实体的Optional对象，如果不存在则返回空Optional
     */
    Optional<InquirySupplierDO> findByIdAndInquiryCode(Long id, String inquiryCode);

    /**
     * 根据询价编码和供应商编码查找供应商实体。
     *
     * @param inquiryCode 询价编码
     * @param code        供应商编码
     * @return 包含指定询价编码和供应商编码的供应商实体的Optional对象，如果不存在则返回空Optional
     */
    Optional<InquirySupplierDO> findByInquiryCodeAndCode(String inquiryCode, String code);

    /**
     * 根据询价编码查询供应商的基本信息。
     *
     * @param inquiryCode 询价编码
     * @return 包含供应商基本信息的列表
     */
    @Query("SELECT is.id as id, is.inquiryCode as inquiryCode, is.code as code, is.name as name, is.contactName as contactName," +
            " is.contactPhone as contactPhone, is.audited as audited, is.confirmed as confirmed" +
            " FROM InquirySupplierDO is WHERE is.inquiryCode = ?1")
    List<InquirySupplierBasic> findSupplierBasicByInquiryCode(String inquiryCode);

    /**
     * 根据询价编码查询供应商的报价信息。
     *
     * @param inquiryCode 询价编码
     * @return 包含供应商报价信息的列表
     */
    @Query("SELECT is.id as id, is.quoteCount as quoteCount, is.quoteCode as quoteCode, is.name as name, is.contactName as contactName," +
            " is.audited as audited, is.confirmed as confirmed" +
            " FROM InquirySupplierDO is WHERE is.inquiryCode = ?1")
    List<InquirySupplierQuote> findSupplierQuoteByInquiryCode(String inquiryCode);

    /**
     * 根据询价编码查找所有相关的供应商实体。
     *
     * @param inquiryCode 询价编码
     * @return 包含所有相关供应商实体的列表
     */
    List<InquirySupplierDO> findByInquiryCode(String inquiryCode);

    /**
     * 根据询价编码删除所有相关的供应商实体。
     *
     * @param inquiryCode 询价编码
     */
    void deleteByInquiryCode(String inquiryCode);
}
