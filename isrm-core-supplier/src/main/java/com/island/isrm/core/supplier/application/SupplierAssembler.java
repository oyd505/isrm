package com.island.isrm.core.supplier.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.Contact;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.dp.SupplierStatus;
import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.domain.entity.SupplierContact;

public class SupplierAssembler {
    public final static SupplierAssembler instance = new SupplierAssembler();

    public Supplier toAddEntity(CreateSupplierCmd command, SupplierCode supplierCode, SupplierStatus supplierStatus) {
        Supplier supplier = new Supplier(supplierCode, supplierStatus);
        this.assemble(command, supplier);
        return supplier;
    }

    private void assemble(CreateSupplierCmd source, Supplier target) {
        target.setName(new Name(source.getName()));
    }

    public Supplier toUpdateEntity(UpdateSupplierCmd command, SupplierStatus supplierStatus) {
        Supplier update = new Supplier(new SupplierCode(command.getSupplierCode()), supplierStatus);
        this.assemble(command, update);
        return update;
    }

    public SupplierContact toAddEntity(SupplierCode supplierCode, AddSupplierContactCmd command) {
        SupplierContact supplierContact = new SupplierContact(null, supplierCode);
        this.assemble(command, supplierContact);
        return supplierContact;
    }

    private void assemble(AddSupplierContactCmd source, SupplierContact target) {
        target.setContact(new Contact(source.getName(), source.getPhone()));
    }

    public SupplierContact toUpdateEntity(SupplierCode supplierCode, UpdateSupplierContactCmd command) {
        SupplierContact update = new SupplierContact(new SupplierContactId(command.getId()), supplierCode);
        this.assemble(command, update);
        return update;
    }
}
