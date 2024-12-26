package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryRequestBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InquiryRequestJpa接口用于处理查询请求相关的数据访问操作
 * 它继承自ListCrudRepository，用于管理InquiryRequestDO实体类
 * 该接口提供了两个自定义查询方法，用于获取按最后修改日期降序排列的查询请求基本信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Repository
public interface InquiryRequestJpa extends ListCrudRepository<InquiryRequestDO, String> {

    /**
     * 获取按最后修改日期降序排列的查询请求基本信息
     * 该方法通过自定义JPQL查询，选择InquiryRequestDO中的部分字段
     * 并根据最后修改日期进行降序排序，返回分页结果
     *
     * @param pageable 分页信息，用于指定当前页和每页大小
     * @return 包含查询请求基本信息的分页结果
     */
    @Query("SELECT ir.inquiryCode as inquiryCode, ir.title as title, ir.buyerName as buyerName, ir.status as status" +
            " FROM InquiryRequestDO ir ORDER BY ir.lastModifiedDate DESC")
    Page<InquiryRequestBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    /**
     * 根据供应商编码获取适用于报价的查询请求基本信息
     * 该方法通过自定义JPQL查询，选择符合条件的InquiryRequestDO中的部分字段
     * 条件包括查询请求状态不为'PENDING'，并且参与方式为'OPEN'或供应商参与了非公开的查询请求
     * 结果根据最后修改日期进行降序排序
     *
     * @param supplierCode 供应商编码，用于筛选适用于该供应商的查询请求
     * @return 包含适用于报价的查询请求基本信息的列表
     */
    @Query("SELECT ir.inquiryCode as inquiryCode, ir.title as title, ir.buyerName as buyerName, ir.status as status" +
            " FROM InquiryRequestDO ir WHERE ir.status != 'PENDING'" +
            " AND (ir.participation = 'OPEN' OR (ir.participation != 'OPEN' AND EXISTS (SELECT s.inquiryCode FROM InquirySupplierDO s WHERE s.inquiryCode = ir.id AND s.code = ?1)))" +
            " ORDER BY ir.lastModifiedDate DESC")
    List<InquiryRequestBasic> findBasicForQuoteOrderByLastModifiedDateDesc(String supplierCode);
}
