package com.island.isrm.core.supplier.port.repo.impl;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.domain.entity.SupplierContact;
import com.island.isrm.core.supplier.domain.repo.SupplierRepository;
import com.island.isrm.core.supplier.port.repo.dao.SupplierContactConverter;
import com.island.isrm.core.supplier.port.repo.dao.SupplierContactJpa;
import com.island.isrm.core.supplier.port.repo.dao.SupplierConverter;
import com.island.isrm.core.supplier.port.repo.dao.SupplierJpa;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SupplierRepositoryImpl implements SupplierRepository {
    private final SupplierJpa supplierJpa;
    private final SupplierConverter supplierConverter;
    private final SupplierContactJpa supplierContactJpa;
    private final SupplierContactConverter supplierContactConverter;

    public SupplierRepositoryImpl(SupplierJpa supplierJpa, SupplierConverter supplierConverter,
                                  SupplierContactJpa supplierContactJpa, SupplierContactConverter supplierContactConverter) {
        this.supplierJpa = supplierJpa;
        this.supplierConverter = supplierConverter;
        this.supplierContactJpa = supplierContactJpa;
        this.supplierContactConverter = supplierContactConverter;
    }

    @Override
    public Supplier find(SupplierCode supplierCode) {
        Optional<SupplierDO> supplierDO = this.supplierJpa.findById(supplierCode.getValue());
        return this.supplierConverter.toEntity(supplierDO.orElseThrow(
                () -> new DataNotFoundException(String.format("供应商(%s)不存在", supplierCode.getValue()))
        ));
    }

    @Override
    public SupplierCode add(Supplier supplier) {
        SupplierDO supplierDO = this.supplierConverter.fromEntity(supplier);
        return new SupplierCode(this.supplierJpa.save(supplierDO).getSupplierCode());
    }

    @Override
    public void update(Supplier supplier) {
        SupplierDO supplierDO = this.supplierConverter.fromEntity(supplier);
        this.supplierJpa.save(supplierDO);
    }

    @Override
    public void removeAll(SupplierCode supplierCode) {
        this.supplierJpa.deleteById(supplierCode.getValue());
        this.supplierContactJpa.deleteBySupplierCode(supplierCode.getValue());
    }

    @Override
    public Supplier findOneSupplierContact(SupplierCode supplierCode, SupplierContactId supplierContactId) {
        Supplier supplier = this.find(supplierCode);
        Optional<SupplierContactDO> supplierContactDO = this.supplierContactJpa.findByIdAndSupplierCode(
                supplierContactId.getId(), supplierCode.getValue()
        );
        SupplierContact supplierContact = this.supplierContactConverter.toEntity(supplierContactDO.orElseThrow(
                () -> new DataNotFoundException(String.format("供应商(%s)联系人(%d)不存在", supplierCode.getValue(), supplierContactId.getId()))
        ));
        supplier.getSupplierContacts().add(supplierContact);
        return supplier;
    }

    @Override
    public SupplierContactId addOneSupplierContact(Supplier supplier) {
        if (supplier == null || supplier.getSupplierContacts().isEmpty()) {
            throw new DataNotFoundException("供应商或供应商联系人列表不存在");
        }
        Optional<SupplierContact> supplierContactOptional = supplier.getSupplierContacts().stream()
                .filter(supplierContact -> supplierContact.getSupplierContactId() == null).findFirst();
        if (supplierContactOptional.isPresent()) {
            SupplierContactDO supplierContactDO = this.supplierContactConverter.fromEntity(supplierContactOptional.get());
            SupplierContactDO savedEntity = this.supplierContactJpa.save(supplierContactDO);
            return new SupplierContactId(savedEntity.getId());
        } else {
            throw new DataNotFoundException("没有添加供应商联系人");
        }
    }

    @Override
    public void updateSupplierContact(Supplier supplier) {
        List<SupplierContactDO> supplierContactDOList = supplier.getSupplierContacts().stream().
                map(this.supplierContactConverter::fromEntity).toList();
        this.supplierContactJpa.saveAll(supplierContactDOList);
    }

    @Override
    public void removeOneSupplierContact(SupplierContactId supplierContactId) {
        this.supplierContactJpa.deleteById(supplierContactId.getId());
    }
}
