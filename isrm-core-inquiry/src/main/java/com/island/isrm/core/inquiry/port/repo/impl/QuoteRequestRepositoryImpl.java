package com.island.isrm.core.inquiry.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.domain.repo.QuoteRequestRepository;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemConverter;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteItemJpa;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestConverter;
import com.island.isrm.core.inquiry.port.repo.dao.QuoteRequestJpa;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 报价请求仓储实现类
 * <p>
 * 该类实现了QuoteRequestRepository接口，提供了报价请求相关操作的具体实现
 * 包括查找、添加、更新和删除报价请求及其明细项
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Component
public class QuoteRequestRepositoryImpl implements QuoteRequestRepository {
    // 报价请求JPA仓储
    private final QuoteRequestJpa quoteRequestJpa;
    // 报价请求转换器，用于在实体和数据对象之间进行转换
    private final QuoteRequestConverter quoteRequestConverter;

    // 报价明细项JPA仓储
    private final QuoteItemJpa quoteItemJpa;
    // 报价明细项转换器，用于在实体和数据对象之间进行转换
    private final QuoteItemConverter quoteItemConverter;

    /**
     * 构造函数
     *
     * @param quoteRequestJpa       报价请求JPA仓储
     * @param quoteRequestConverter 报价请求转换器
     * @param quoteItemJpa          报价明细项JPA仓储
     * @param quoteItemConverter    报价明细项转换器
     */
    public QuoteRequestRepositoryImpl(QuoteRequestJpa quoteRequestJpa, QuoteRequestConverter quoteRequestConverter,
                                      QuoteItemJpa quoteItemJpa, QuoteItemConverter quoteItemConverter) {
        this.quoteRequestJpa = quoteRequestJpa;
        this.quoteRequestConverter = quoteRequestConverter;
        this.quoteItemJpa = quoteItemJpa;
        this.quoteItemConverter = quoteItemConverter;
    }

    /**
     * 根据报价编码查找报价请求
     *
     * @param quoteCode 报价编码
     * @return 对应的报价请求实体
     * @throws DataNotFoundException 如果找不到对应的报价请求，则抛出数据不存在异常
     */
    @Override
    public QuoteRequest find(QuoteCode quoteCode) {
        Optional<QuoteRequestDO> quoteRequestDO = this.quoteRequestJpa.findById(quoteCode.getValue());
        return this.quoteRequestConverter.toEntity(quoteRequestDO.orElseThrow(
                () -> new DataNotFoundException(String.format("报价申请(%s)不存在", quoteCode.getValue()))
        ));
    }

    /**
     * 查找报价请求及其所有明细项
     *
     * @param quoteCode 报价编码
     * @return 包含所有明细项的报价请求实体
     */
    @Override
    public QuoteRequest findAll(QuoteCode quoteCode) {
        QuoteRequest quoteRequest = find(quoteCode);
        List<QuoteItemDO> quoteItemDOList = this.quoteItemJpa.findByQuoteCode(quoteCode.getValue());
        quoteItemDOList.forEach(quoteItemDO -> quoteRequest.getQuoteItemList().add(
                this.quoteItemConverter.toEntity(quoteItemDO)
        ));
        return quoteRequest;
    }

    /**
     * 查找仅处于待处理状态的报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 可选的报价请求实体，如果存在的话
     */
    @Override
    public Optional<QuoteRequest> findOnlyPending(InquiryCode inquiryCode, SupplierCode supplierCode) {
        List<QuoteRequestDO> quoteRequestDOList = this.quoteRequestJpa.findByInquiryCodeAndSupplierCodeAndStatus(
                inquiryCode.getValue(), supplierCode.getValue(), QuoteStatus.PENDING.name());
        return quoteRequestDOList.stream().findFirst().map(this.quoteRequestConverter::toEntity);
    }

    /**
     * 查找仅处于已提交状态的报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 可选的报价请求实体，如果存在的话
     */
    @Override
    public Optional<QuoteRequest> findOnlySubmitted(InquiryCode inquiryCode, SupplierCode supplierCode) {
        List<QuoteRequestDO> quoteRequestDOList = this.quoteRequestJpa.findByInquiryCodeAndSupplierCodeAndStatus(
                inquiryCode.getValue(), supplierCode.getValue(), QuoteStatus.SUBMITTED.name());
        return quoteRequestDOList.stream().findFirst().map(this.quoteRequestConverter::toEntity);
    }

    /**
     * 添加报价请求及其所有明细项
     *
     * @param quoteRequest 报价请求实体，包含明细项列表
     * @return 添加后的报价编码
     */
    @Override
    public QuoteCode addAll(QuoteRequest quoteRequest) {
        List<QuoteItemDO> quoteItemDOList = quoteRequest.getQuoteItemList().stream()
                .map(this.quoteItemConverter::fromEntity).toList();
        this.quoteItemJpa.saveAll(quoteItemDOList);
        QuoteRequestDO quoteRequestDO = this.quoteRequestConverter.fromEntity(quoteRequest);
        return new QuoteCode(this.quoteRequestJpa.save(quoteRequestDO).getQuoteCode());
    }

    /**
     * 更新报价请求
     *
     * @param quoteRequest 报价请求实体
     */
    @Override
    public void update(QuoteRequest quoteRequest) {
        QuoteRequestDO quoteRequestDO = this.quoteRequestConverter.fromEntity(quoteRequest);
        this.quoteRequestJpa.save(quoteRequestDO);
    }

    /**
     * 删除报价请求及其所有明细项
     *
     * @param quoteCode 报价编码
     */
    @Override
    public void removeAll(QuoteCode quoteCode) {
        this.quoteRequestJpa.deleteById(quoteCode.getValue());
        this.quoteItemJpa.deleteByQuoteCode(quoteCode.getValue());
    }

    /**
     * 查找报价请求中的一个明细项
     *
     * @param quoteCode   报价编码
     * @param quoteItemId 报价明细项ID
     * @return 包含指定明细项的报价请求实体
     * @throws DataNotFoundException 如果找不到对应的报价请求或明细项，则抛出数据不存在异常
     */
    @Override
    public QuoteRequest findOneQuoteItem(QuoteCode quoteCode, QuoteItemId quoteItemId) {
        QuoteRequest quoteRequest = find(quoteCode);
        Optional<QuoteItemDO> quoteItemDO = this.quoteItemJpa.findByIdAndQuoteCode(quoteItemId.getId(), quoteCode.getValue());
        QuoteItem quoteItem = this.quoteItemConverter.toEntity(quoteItemDO.orElseThrow(
                () -> new DataNotFoundException(String.format("报价申请(%s)明细项(%d)不存在", quoteCode.getValue(), quoteItemId.getId()))
        ));
        quoteRequest.getQuoteItemList().add(quoteItem);
        return quoteRequest;
    }

    /**
     * 更新报价请求中的明细项
     *
     * @param quoteRequest 报价请求实体，包含更新后的明细项列表
     */
    @Override
    public void updateQuoteItem(QuoteRequest quoteRequest) {
        List<QuoteItemDO> quoteItemDOList = quoteRequest.getQuoteItemList().stream()
                .map(this.quoteItemConverter::fromEntity).toList();
        this.quoteItemJpa.saveAll(quoteItemDOList);
    }
}
