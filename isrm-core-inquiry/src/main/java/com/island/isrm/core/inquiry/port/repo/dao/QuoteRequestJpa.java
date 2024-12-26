package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报价请求JPA接口，用于处理报价请求相关的数据访问操作
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Repository
public interface QuoteRequestJpa extends ListCrudRepository<QuoteRequestDO, String> {
    /**
     * 根据询价编码、供应商编码和状态查找报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @param status       状态
     * @return 匹配条件的报价请求列表
     */
    List<QuoteRequestDO> findByInquiryCodeAndSupplierCodeAndStatus(String inquiryCode, String supplierCode, String status);

    /**
     * 根据询价编码和供应商编码查找基本的报价请求信息
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 匹配条件的基本报价请求信息列表
     */
    @Query("SELECT qr.quoteCode as quoteCode, qr.supplierName as supplierName, qr.status as status, qr.inquiryCode as inquiryCode" +
            " FROM QuoteRequestDO qr WHERE qr.inquiryCode = ?1 AND qr.supplierCode = ?2")
    List<QuoteRequestBasic> findBasicByInquiryCodeAndSupplierCode(String inquiryCode, String supplierCode);
}
