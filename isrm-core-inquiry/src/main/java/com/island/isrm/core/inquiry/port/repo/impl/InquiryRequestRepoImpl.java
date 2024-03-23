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

@Component
public class InquiryRequestRepoImpl implements InquiryRequestRepository {
    private final InquiryRequestJpa inquiryRequestJpa;
    private final InquiryRequestConverter inquiryRequestConverter;

    private final InquiryProductJpa inquiryProductJpa;
    private final InquiryProductConverter inquiryProductConverter;

    private final InquirySupplierJpa inquirySupplierJpa;
    private final InquirySupplierConverter inquirySupplierConverter;

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

    @Override
    public InquiryRequest find(InquiryCode inquiryCode) {
        Optional<InquiryRequestDO> inquiryRequestDO = this.inquiryRequestJpa.findById(inquiryCode.getValue());
        return this.inquiryRequestConverter.toEntity(inquiryRequestDO.orElseThrow(
                () -> new DataNotFoundException(String.format("询价申请(%s)不存在", inquiryCode.getValue()))
        ));
    }

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

    @Override
    public InquiryCode add(InquiryRequest inquiryRequest) {
        InquiryRequestDO inquiryRequestDO = this.inquiryRequestConverter.fromEntity(inquiryRequest);
        return new InquiryCode(this.inquiryRequestJpa.save(inquiryRequestDO).getInquiryCode());
    }

    @Override
    public void update(InquiryRequest inquiryRequest) {
        InquiryRequestDO inquiryRequestDO = this.inquiryRequestConverter.fromEntity(inquiryRequest);
        this.inquiryRequestJpa.save(inquiryRequestDO);
    }

    @Override
    public void updateAndInquiryProduct(InquiryRequest inquiryRequest) {
        this.update(inquiryRequest);
        this.updateInquiryProduct(inquiryRequest);
    }

    @Override
    public void removeAll(InquiryCode inquiryCode) {
        this.inquiryRequestJpa.deleteById(inquiryCode.getValue());
        this.inquiryProductJpa.deleteByInquiryCode(inquiryCode.getValue());
        this.inquirySupplierJpa.deleteByInquiryCode(inquiryCode.getValue());
    }

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

    @Override
    public void updateInquiryProduct(InquiryRequest inquiryRequest) {
        List<InquiryProductDO> inquiryProductDOList = inquiryRequest.getInquiryProducts().stream().
                map(this.inquiryProductConverter::fromEntity).toList();
        this.inquiryProductJpa.saveAll(inquiryProductDOList);
    }

    @Override
    public void removeOneInquiryProduct(InquiryProductId inquiryProductId) {
        this.inquiryProductJpa.deleteById(inquiryProductId.getId());
    }

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

    @Override
    public void updateInquirySupplier(InquiryRequest inquiryRequest) {
        List<InquirySupplierDO> inquirySupplierDOList = inquiryRequest.getInquirySuppliers().stream().
                map(this.inquirySupplierConverter::fromEntity).toList();
        this.inquirySupplierJpa.saveAll(inquirySupplierDOList);
    }

    @Override
    public void removeOneInquirySupplier(InquirySupplierId inquirySupplierId) {
        this.inquirySupplierJpa.deleteById(inquirySupplierId.getId());
    }
}
