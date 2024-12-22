package com.island.isrm.core.supplier.application.impl;

import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierAppServiceImpl implements SupplierAppService {
    private final SupplierAssembler supplierAssembler = SupplierAssembler.instance;
    private final SupplierRepository supplierRepository;
    private final SupplierCodeService supplierCodeService;
    private final ApplicationEventPublisher publisher;

    public SupplierAppServiceImpl(SupplierRepository supplierRepository, SupplierCodeService supplierCodeService, ApplicationEventPublisher publisher) {
        this.supplierRepository = supplierRepository;
        this.supplierCodeService = supplierCodeService;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public SupplierCode create(CreateSupplierCmd command) {
        SupplierCode supplierCode = this.supplierCodeService.generateSerialNumber();
        Supplier supplier = this.supplierAssembler.toAddEntity(command, supplierCode, SupplierStatus.POTENTIAL);
        this.supplierRepository.add(supplier);
        // 发布事件
        this.publisher.publishEvent(new SupplierCreatedEvent(this, supplierCode.getValue(), supplier.getName().getValue()));
        return supplierCode;
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
        SupplierContactId supplierContactId = this.supplierRepository.addOneSupplierContact(supplier);
        this.publisher.publishEvent(new SupplierContactCreatedEvent(this, supplierCode.getValue(),
                supplier.getName().getValue(), command.getPhone(), command.getName()));
        return supplierContactId;
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
