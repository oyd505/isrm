package com.island.isrm.core.inquiry.port.repo.service;

import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemJpa;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报价查询服务类，提供与报价相关的查询方法
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class QuoteQueryService {
    // 注入报价请求数据访问对象
    private final QuoteRequestJpa quoteRequestJpa;
    // 注入报价项数据访问对象
    private final QuoteItemJpa quoteItemJpa;

    /**
     * 构造函数，用于注入依赖
     *
     * @param quoteRequestJpa 报价请求JPA接口
     * @param quoteItemJpa    报价项JPA接口
     */
    public QuoteQueryService(QuoteRequestJpa quoteRequestJpa, QuoteItemJpa quoteItemJpa) {
        this.quoteRequestJpa = quoteRequestJpa;
        this.quoteItemJpa = quoteItemJpa;
    }

    /**
     * 根据询价编码和供应商编码查找基本的报价请求信息
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 报价请求基本信息列表
     */
    public List<QuoteRequestBasic> findBasicByInquiryCodeAndSupplierCode(String inquiryCode, String supplierCode) {
        return this.quoteRequestJpa.findBasicByInquiryCodeAndSupplierCode(inquiryCode, supplierCode);
    }

    /**
     * 根据报价编码查找报价请求详细信息
     *
     * @param quoteCode 报价编码
     * @return 报价请求详细信息对象
     */
    public QuoteRequestDO findById(String quoteCode) {
        return this.quoteRequestJpa.findById(quoteCode).orElse(new QuoteRequestDO());
    }

    /**
     * 根据报价编码查找报价项基本信息
     *
     * @param quoteCode 报价编码
     * @return 报价项基本信息列表
     */
    public List<QuoteItemBasic> findItemBasicByQuoteCode(String quoteCode) {
        return this.quoteItemJpa.findItemBasicByQuoteCode(quoteCode);
    }

    /**
     * 根据报价项ID和报价编码查找报价项详细信息
     *
     * @param quoteItemId 报价项ID
     * @param quoteCode   报价编码
     * @return 报价项详细信息对象
     */
    public QuoteItemDO findByQuoteItemIdAndQuoteCode(Long quoteItemId, String quoteCode) {
        return this.quoteItemJpa.findByIdAndQuoteCode(quoteItemId, quoteCode).orElse(new QuoteItemDO());
    }
}
