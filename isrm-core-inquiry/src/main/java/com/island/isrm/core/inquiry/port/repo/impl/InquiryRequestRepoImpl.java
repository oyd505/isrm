package com.island.isrm.core.inquiry.port.repo.impl;

import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryProductId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquirySupplierId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;
import com.island.isrm.core.inquiry.domain.repo.InquiryRequestRepository;
import com.island.isrm.core.inquiry.port.repo.dao.*;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 询价请求仓储接口实现类
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Component
public class InquiryRequestRepoImpl implements InquiryRequestRepository {
    private final InquiryRequestJpa inquiryRequestJpa;
    private final InquiryRequestConverter inquiryRequestConverter;

    private final InquiryProductJpa inquiryProductJpa;
    private final InquiryProductConverter inquiryProductConverter;

    private final InquirySupplierJpa inquirySupplierJpa;
    private final InquirySupplierConverter inquirySupplierConverter;

    /**
     * 构造函数
     *
     * @param inquiryRequestJpa        询价请求数据访问接口
     * @param inquiryRequestConverter  询价请求转换器
     * @param inquiryProductJpa        询价商品数据访问接口
     * @param inquiryProductConverter  询价商品转换器
     * @param inquirySupplierJpa       询价供应商数据访问接口
     * @param inquirySupplierConverter 询价供应商转换器
     */
    public InquiryRequestRepoImpl(InquiryRequestJpa inquiryRequestJpa, InquiryRequestConverter inquiryRequestConverter,
                                  InquiryProductJpa inquiryProductJpa, InquiryProductConverter inquiryProductConverter,
                                  InquirySupplierJpa inquirySupplierJpa, InquirySupplierConverter inquirySupplierConverter) {
        this.inquiryRequestJpa = inquiryRequestJpa;
        this.inquiryRequestConverter = inquiryRequestConverter;
        this.inquiryProductJpa = inquiryProductJpa;
        this.inquiryProductConverter = inquiryProductConverter;
        this.inquirySupplierJpa = inquirySupplierJpa;
        this.inquirySupplierConverter = inquirySupplierConverter;
    }

    /**
     * 查找询价申请
     *
     * @param inquiryCode 询价编码
     * @return 询价请求实体
     */
    @Override
    public InquiryRequest find(InquiryCode inquiryCode) {
        Optional<InquiryRequestDO> inquiryRequestDO = this.inquiryRequestJpa.findById(inquiryCode.getValue());
        return this.inquiryRequestConverter.toEntity(inquiryRequestDO.orElseThrow(
                () -> new DataNotFoundException(String.format("询价申请(%s)不存在", inquiryCode.getValue()))
        ));
    }

    /**
     * 查找询价申请，同时查询询价商品和询价供应商
     *
     * @param inquiryCode 询价编码
     * @return 询价请求实体
     */
    @Override
    public InquiryRequest findAll(InquiryCode inquiryCode) {
        InquiryRequest inquiryRequest = this.find(inquiryCode);
        List<InquiryProductDO> inquiryProductDOList = this.inquiryProductJpa.findByInquiryCode(inquiryCode.getValue());
        inquiryProductDOList.forEach(inquiryProductDO -> inquiryRequest.getInquiryProducts().add(
                this.inquiryProductConverter.toEntity(inquiryProductDO)
        ));
        List<InquirySupplierDO> inquirySupplierDOList = this.inquirySupplierJpa.findByInquiryCode(inquiryCode.getValue());
        inquirySupplierDOList.forEach(inquirySupplierDO -> inquiryRequest.getInquirySuppliers().add(
                this.inquirySupplierConverter.toEntity(inquirySupplierDO)
        ));
        return inquiryRequest;
    }

    /**
     * 添加询价请求
     *
     * @param inquiryRequest 询价请求实体
     * @return 询价编码
     */
    @Override
    public InquiryCode add(InquiryRequest inquiryRequest) {
        InquiryRequestDO inquiryRequestDO = this.inquiryRequestConverter.fromEntity(inquiryRequest);
        return new InquiryCode(this.inquiryRequestJpa.save(inquiryRequestDO).getInquiryCode());
    }

    /**
     * 更新询价请求
     *
     * @param inquiryRequest 询价请求实体
     */
    @Override
    public void update(InquiryRequest inquiryRequest) {
        InquiryRequestDO inquiryRequestDO = this.inquiryRequestConverter.fromEntity(inquiryRequest);
        this.inquiryRequestJpa.save(inquiryRequestDO);
    }

    /**
     * 更新询价请求并同时更新询价商品
     *
     * @param inquiryRequest 询价请求实体
     */
    @Override
    public void updateAndInquiryProduct(InquiryRequest inquiryRequest) {
        this.update(inquiryRequest);
        this.updateInquiryProduct(inquiryRequest);
    }

    /**
     * 移除所有与特定询价编码相关的记录
     *
     * @param inquiryCode 询价编码
     */
    @Override
    public void removeAll(InquiryCode inquiryCode) {
        this.inquiryRequestJpa.deleteById(inquiryCode.getValue());
        this.inquiryProductJpa.deleteByInquiryCode(inquiryCode.getValue());
        this.inquirySupplierJpa.deleteByInquiryCode(inquiryCode.getValue());
    }

    /**
     * 查找特定询价编码和询价商品ID对应的询价请求
     *
     * @param inquiryCode      询价编码
     * @param inquiryProductId 询价商品ID
     * @return 询价请求实体
     */
    @Override
    public InquiryRequest findOneInquiryProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId) {
        InquiryRequest inquiryRequest = this.find(inquiryCode);
        Optional<InquiryProductDO> inquiryProductDO = this.inquiryProductJpa.findByIdAndInquiryCode(
                inquiryProductId.getId(), inquiryCode.getValue()
        );
        InquiryProduct inquiryProduct = this.inquiryProductConverter.toEntity(inquiryProductDO.orElseThrow(
                () -> new DataNotFoundException(
                        String.format("询价申请(%s)商品(%d)不存在", inquiryCode.getValue(), inquiryProductId.getId())
                )
        ));
        inquiryRequest.getInquiryProducts().add(inquiryProduct);
        return inquiryRequest;
    }

    /**
     * 添加一个询价商品到询价请求中
     *
     * @param inquiryRequest 询价请求实体
     * @return 新增的询价商品ID
     */
    @Override
    public InquiryProductId addOneInquiryProduct(InquiryRequest inquiryRequest) {
        if (inquiryRequest == null || inquiryRequest.getInquiryProducts() == null) {
            throw new DataNotFoundException("询价申请或询价申请商品不存在");
        }
        Optional<InquiryProduct> inquiryProductOptional = inquiryRequest.getInquiryProducts().stream()
                .filter(inquiryProduct -> inquiryProduct.getInquiryProductId() == null).findFirst();
        if (inquiryProductOptional.isPresent()) {
            InquiryProductDO inquiryProductDO = this.inquiryProductConverter.fromEntity(inquiryProductOptional.get());
            InquiryProductDO savedEntity = this.inquiryProductJpa.save(inquiryProductDO);
            return new InquiryProductId(savedEntity.getId());
        } else {
            throw new DataNotFoundException("询价申请商品不存在");
        }
    }

    /**
     * 更新询价请求中的所有询价商品
     *
     * @param inquiryRequest 询价请求实体
     */
    @Override
    public void updateInquiryProduct(InquiryRequest inquiryRequest) {
        List<InquiryProductDO> inquiryProductDOList = inquiryRequest.getInquiryProducts().stream().
                map(this.inquiryProductConverter::fromEntity).toList();
        this.inquiryProductJpa.saveAll(inquiryProductDOList);
    }

    /**
     * 移除特定ID的询价商品
     *
     * @param inquiryProductId 询价商品ID
     */
    @Override
    public void removeOneInquiryProduct(InquiryProductId inquiryProductId) {
        this.inquiryProductJpa.deleteById(inquiryProductId.getId());
    }

    /**
     * 查找特定询价编码和供应商ID对应的询价请求
     *
     * @param inquiryCode       询价编码
     * @param inquirySupplierId 供应商ID
     * @return 询价请求实体
     */
    @Override
    public InquiryRequest findOneInquirySupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId) {
        InquiryRequest inquiryRequest = this.find(inquiryCode);
        Optional<InquirySupplierDO> inquirySupplierDO = this.inquirySupplierJpa.findByIdAndInquiryCode(
                inquirySupplierId.getId(), inquiryCode.getValue()
        );
        InquirySupplier inquirySupplier = this.inquirySupplierConverter.toEntity(inquirySupplierDO.orElseThrow(
                () -> new DataNotFoundException(
                        String.format("询价申请(%s)供应商(%d)不存在", inquiryCode.getValue(), inquirySupplierId.getId()))
        ));
        inquiryRequest.getInquirySuppliers().add(inquirySupplier);
        return inquiryRequest;
    }

    /**
     * 根据供应商编码查找特定询价编码对应的询价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 询价请求实体
     */
    @Override
    public InquiryRequest findOneInquirySupplier(InquiryCode inquiryCode, SupplierCode supplierCode) {
        InquiryRequest inquiryRequest = this.find(inquiryCode);
        Optional<InquirySupplierDO> inquirySupplierDO = this.inquirySupplierJpa.findByInquiryCodeAndCode(
                inquiryCode.getValue(), supplierCode.getValue()
        );
        inquirySupplierDO.ifPresent(supplier -> {
            InquirySupplier inquirySupplier = this.inquirySupplierConverter.toEntity(supplier);
            inquiryRequest.getInquirySuppliers().add(inquirySupplier);
        });
        return inquiryRequest;
    }

    /**
     * 添加一个询价供应商到询价请求中
     *
     * @param inquiryRequest 询价请求实体
     * @return 新增的询价供应商ID
     */
    @Override
    public InquirySupplierId addOneInquirySupplier(InquiryRequest inquiryRequest) {
        if (inquiryRequest == null || inquiryRequest.getInquirySuppliers() == null) {
            throw new DataNotFoundException("询价申请或询价申请供应商不存在");
        }
        Optional<InquirySupplier> inquirySupplierOptional = inquiryRequest.getInquirySuppliers().stream()
                .filter(inquirySupplier -> inquirySupplier.getInquirySupplierId() == null).findFirst();
        if (inquirySupplierOptional.isPresent()) {
            InquirySupplierDO inquirySupplierDO = this.inquirySupplierConverter.fromEntity(inquirySupplierOptional.get());
            InquirySupplierDO savedEntity = this.inquirySupplierJpa.save(inquirySupplierDO);
            return new InquirySupplierId(savedEntity.getId());
        } else {
            throw new BizException("供应商已存在询价供应商列表");
        }
    }

    /**
     * 更新询价请求中的所有询价供应商
     *
     * @param inquiryRequest 询价请求实体
     */
    @Override
    public void updateInquirySupplier(InquiryRequest inquiryRequest) {
        List<InquirySupplierDO> inquirySupplierDOList = inquiryRequest.getInquirySuppliers().stream().
                map(this.inquirySupplierConverter::fromEntity).toList();
        this.inquirySupplierJpa.saveAll(inquirySupplierDOList);
    }

    /**
     * 移除特定ID的询价供应商
     *
     * @param inquirySupplierId 询价供应商ID
     */
    @Override
    public void removeOneInquirySupplier(InquirySupplierId inquirySupplierId) {
        this.inquirySupplierJpa.deleteById(inquirySupplierId.getId());
    }
}
