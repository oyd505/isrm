package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.domain.entity.SupplierContact;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 供应商联系人转换器，用于在实体和数据对象之间进行转换
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Mapper(componentModel = "Spring")
public interface SupplierContactConverter {
    // SupplierContactConverter的实例，用于执行转换操作
    SupplierConverter INSTANCE = Mappers.getMapper(SupplierConverter.class);

    /**
     * 从SupplierContact实体转换为SupplierContactDO数据对象
     *
     * @param supplierContact 供应商联系人实体，包含供应商联系人相关信息
     * @return 转换后的供应商联系人数据对象
     */
    @Mapping(source = "supplierCode.value", target = "supplierCode")
    @Mapping(source = "supplierContactId.id", target = "id")
    @Mapping(source = "contact.name", target = "name")
    @Mapping(source = "contact.phone", target = "phone")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    SupplierContactDO fromEntity(SupplierContact supplierContact);

    /**
     * 从SupplierContactDO数据对象转换为SupplierContact实体
     *
     * @param supplierContactDO 供应商联系人数据对象，包含供应商联系人相关信息
     * @return 转换后的供应商联系人实体
     */
    @Mapping(target = "supplierCode.value", source = "supplierCode")
    @Mapping(target = "supplierContactId.id", source = "id")
    @Mapping(target = "contact.name", source = "name")
    @Mapping(target = "contact.phone", source = "phone")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    SupplierContact toEntity(SupplierContactDO supplierContactDO);
}
