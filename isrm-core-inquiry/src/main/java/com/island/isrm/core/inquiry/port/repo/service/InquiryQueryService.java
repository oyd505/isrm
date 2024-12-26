package com.island.isrm.core.inquiry.port.repo.service;

import com.island.isrm.core.inquiry.port.repo.dao.InquiryProductJpa;
import com.island.isrm.core.inquiry.port.repo.dao.InquiryRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.InquirySupplierJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryProductBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryRequestBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierQuote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * InquiryQueryService类提供了与查询相关的服务方法，主要涉及到查询请购单、请购单商品和供应商报价信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class InquiryQueryService {
    private final InquiryRequestJpa inquiryRequestJpa;
    private final InquiryProductJpa inquiryProductJpa;
    private final InquirySupplierJpa inquirySupplierJpa;

    /**
     * 构造函数，通过构造器注入InquiryRequestJpa, InquiryProductJpa, InquirySupplierJpa
     *
     * @param inquiryRequestJpa  请购单JPA接口
     * @param inquiryProductJpa  请购单商品JPA接口
     * @param inquirySupplierJpa 供应商报价JPA接口
     */
    public InquiryQueryService(InquiryRequestJpa inquiryRequestJpa,
                               InquiryProductJpa inquiryProductJpa,
                               InquirySupplierJpa inquirySupplierJpa) {
        this.inquiryRequestJpa = inquiryRequestJpa;
        this.inquiryProductJpa = inquiryProductJpa;
        this.inquirySupplierJpa = inquirySupplierJpa;
    }

    /**
     * 按最后修改日期降序查询基本请购单信息
     *
     * @param pageable 分页信息
     * @return 分页的基本请购单信息
     */
    public Page<InquiryRequestBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable) {
        return this.inquiryRequestJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    /**
     * 根据供应商编码按最后修改日期降序查询可供报价的基本请购单信息
     *
     * @param supplierCode 供应商编码
     * @return 可供报价的基本请购单信息列表
     */
    public List<InquiryRequestBasic> findBasicForQuoteOrderByLastModifiedDateDesc(String supplierCode) {
        return this.inquiryRequestJpa.findBasicForQuoteOrderByLastModifiedDateDesc(supplierCode);
    }

    /**
     * 通过请购单编码查找请购单详细信息
     *
     * @param inquiryCode 请购单编码
     * @return 请购单详细信息，如果找不到则返回一个新的请购单DO对象
     */
    public InquiryRequestDO findById(String inquiryCode) {
        return this.inquiryRequestJpa.findById(inquiryCode).orElse(new InquiryRequestDO());
    }

    /**
     * 通过请购单编码查找请购单商品基本信息
     *
     * @param inquiryCode 请购单编码
     * @return 请购单商品基本信息列表
     */
    public List<InquiryProductBasic> findProductBasicByInquiryCode(String inquiryCode) {
        return this.inquiryProductJpa.findProductBasicByInquiryCode(inquiryCode);
    }

    /**
     * 通过请购单商品ID和请购单编码查找请购单商品详细信息
     *
     * @param inquiryProductId 请购单商品ID
     * @param inquiryCode      请购单编码
     * @return 请购单商品详细信息，如果找不到则返回一个新的请购单商品DO对象
     */
    public InquiryProductDO findByInquiryProductIdAndInquiryCode(Long inquiryProductId, String inquiryCode) {
        return this.inquiryProductJpa.findByIdAndInquiryCode(inquiryProductId, inquiryCode).orElse(new InquiryProductDO());
    }

    /**
     * 通过请购单编码查找供应商基本信息
     *
     * @param inquiryCode 请购单编码
     * @return 供应商基本信息列表
     */
    public List<InquirySupplierBasic> findSupplierBasicByInquiryCode(String inquiryCode) {
        return this.inquirySupplierJpa.findSupplierBasicByInquiryCode(inquiryCode);
    }

    /**
     * 通过请购单编码查找供应商报价信息
     *
     * @param inquiryCode 请购单编码
     * @return 供应商报价信息列表
     */
    public List<InquirySupplierQuote> findSupplierQuoteByInquiryCode(String inquiryCode) {
        return this.inquirySupplierJpa.findSupplierQuoteByInquiryCode(inquiryCode);
    }

    /**
     * 通过供应商ID和请购单编码查找供应商详细信息
     *
     * @param inquirySupplierId 供应商ID
     * @param inquiryCode       请购单编码
     * @return 供应商详细信息，如果找不到则返回一个新的供应商DO对象
     */
    public InquirySupplierDO findByInquirySupplierIdAndInquiryCode(Long inquirySupplierId, String inquiryCode) {
        return this.inquirySupplierJpa.findByIdAndInquiryCode(inquirySupplierId, inquiryCode).orElse(new InquirySupplierDO());
    }

    /**
     * 通过请购单编码和供应商编码查找供应商详细信息
     *
     * @param inquiryCode  请购单编码
     * @param supplierCode 供应商编码
     * @return 供应商详细信息，如果找不到则返回一个新的供应商DO对象
     */
    public InquirySupplierDO findByInquiryCodeAndCode(String inquiryCode, String supplierCode) {
        return this.inquirySupplierJpa.findByInquiryCodeAndCode(inquiryCode, supplierCode).orElse(new InquirySupplierDO());
    }

}
