package com.island.isrm.core.supplier.application.impl;

import com.island.isrm.core.supplier.application.SupplierAppService;
import com.island.isrm.core.supplier.application.SupplierAssembler;
import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.dp.SupplierStatus;
import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.domain.entity.SupplierContact;
import com.island.isrm.core.supplier.domain.external.SupplierCodeService;
import com.island.isrm.core.supplier.domain.repo.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierAppServiceImpl implements SupplierAppService {
    private final SupplierAssembler supplierAssembler = SupplierAssembler.instance;
    private final SupplierRepository supplierRepository;
    private final SupplierCodeService supplierCodeService;

    public SupplierAppServiceImpl(SupplierRepository supplierRepository, SupplierCodeService supplierCodeService) {
        this.supplierRepository = supplierRepository;
        this.supplierCodeService = supplierCodeService;
    }

    @Transactional
    @Override
    public SupplierCode create(CreateSupplierCmd command) {
        SupplierCode supplierCode = this.supplierCodeService.generateSerialNumber();
        Supplier supplier = this.supplierAssembler.toAddEntity(command, supplierCode, SupplierStatus.POTENTIAL);
        return this.supplierRepository.add(supplier);
    }

    @Transactional
    @Override
    public void update(UpdateSupplierCmd command) {
        Supplier input = this.supplierAssembler.toUpdateEntity(command, SupplierStatus.POTENTIAL);
        Supplier supplier = this.supplierRepository.find(input.getSupplierCode());
        supplier.update(input);
        this.supplierRepository.update(supplier);
    }

    @Transactional
    @Override
    public void remove(SupplierCode supplierCode) {
        Supplier supplier = this.supplierRepository.find(supplierCode);
        supplier.checkEditable();
        this.supplierRepository.removeAll(supplierCode);
    }

    @Override
    public SupplierContactId addContact(SupplierCode supplierCode, AddSupplierContactCmd command) {
        Supplier supplier = this.supplierRepository.find(supplierCode);
        SupplierContact supplierContact = this.supplierAssembler.toAddEntity(supplierCode, command);
        supplier.addContact(supplierContact);
        return this.supplierRepository.addOneSupplierContact(supplier);
    }

    @Override
    public void updateContact(SupplierCode supplierCode, UpdateSupplierContactCmd command) {
        SupplierContact input = this.supplierAssembler.toUpdateEntity(supplierCode, command);
        Supplier supplier = this.supplierRepository.findOneSupplierContact(
                supplierCode, input.getSupplierContactId()
        );
        supplier.updateContact(input);
        this.supplierRepository.updateSupplierContact(supplier);
    }

    @Override
    public void removeContact(SupplierCode supplierCode, SupplierContactId supplierContactId) {
        this.supplierRepository.findOneSupplierContact(supplierCode, supplierContactId);
        this.supplierRepository.removeOneSupplierContact(supplierContactId);
    }
}
