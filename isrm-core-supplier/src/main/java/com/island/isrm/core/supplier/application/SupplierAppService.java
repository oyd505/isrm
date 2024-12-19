package com.island.isrm.core.supplier.application;

import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface SupplierAppService {

    SupplierCode create(@Valid CreateSupplierCmd command);

    void update(@Valid UpdateSupplierCmd command);

    void remove(SupplierCode supplierCode);

    SupplierContactId addContact(SupplierCode supplierCode, @Valid AddSupplierContactCmd command);

    void updateContact(SupplierCode supplierCode, @Valid UpdateSupplierContactCmd command);

    void removeContact(SupplierCode supplierCode, SupplierContactId supplierContactId);
}
