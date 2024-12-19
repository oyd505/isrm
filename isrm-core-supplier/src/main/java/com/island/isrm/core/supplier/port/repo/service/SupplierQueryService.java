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

@Service
public class SupplierQueryService {
    private final SupplierJpa supplierJpa;
    private final SupplierContactJpa supplierContactJpa;

    public SupplierQueryService(SupplierJpa supplierJpa, SupplierContactJpa supplierContactJpa) {
        this.supplierJpa = supplierJpa;
        this.supplierContactJpa = supplierContactJpa;
    }

    public Page<SupplierBasic> pageBasic(Pageable pageable) {
        return this.supplierJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    public List<SupplierCodeAndName> listCodeAndName() {
        return this.supplierJpa.listCodeAndName();
    }

    public SupplierDO find(String supplierCode) {
        return this.supplierJpa.findById(supplierCode).orElse(new SupplierDO());
    }

    public List<SupplierContactBasic> listContactBasic(String supplierCode) {
        return this.supplierContactJpa.findContactBasicBySupplierCode(supplierCode);
    }

    public List<ContactNameAndPhone> listContactNameAndPhone(String supplierCode) {
        return this.supplierContactJpa.listContactNameAndPhone(supplierCode);
    }

    public SupplierContactDO findContact(Long id, String supplierCode) {
        return this.supplierContactJpa.findByIdAndSupplierCode(id, supplierCode).orElse(new SupplierContactDO());
    }
}
