package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.domain.entity.SupplierContact;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface SupplierContactConverter {
    SupplierConverter INSTANCE = Mappers.getMapper(SupplierConverter.class);

    @Mapping(source = "supplierCode.value", target = "supplierCode")
    @Mapping(source = "supplierContactId.id", target = "id")
    @Mapping(source = "contact.name", target = "name")
    @Mapping(source = "contact.phone", target = "phone")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    SupplierContactDO fromEntity(SupplierContact supplierContact);

    @Mapping(target = "supplierCode.value", source = "supplierCode")
    @Mapping(target = "supplierContactId.id", source = "id")
    @Mapping(target = "contact.name", source = "name")
    @Mapping(target = "contact.phone", source = "phone")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    SupplierContact toEntity(SupplierContactDO supplierContactDO);
}
