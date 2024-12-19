package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface SupplierConverter {
    SupplierConverter INSTANCE = Mappers.getMapper(SupplierConverter.class);

    @Mapping(source = "supplierCode.value", target = "supplierCode")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "supplierStatus", target = "supplierStatus")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    SupplierDO fromEntity(Supplier supplier);

    @Mapping(target = "supplierCode.value", source = "supplierCode")
    @Mapping(target = "name.value", source = "name")
    @Mapping(target = "supplierStatus", source = "supplierStatus")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    Supplier toEntity(SupplierDO supplierDO);
}
