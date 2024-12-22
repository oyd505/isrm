package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 供应商实体与数据对象转换器接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Mapper(componentModel = "Spring")
public interface SupplierConverter {
    // SupplierConverter的实例，通过Mappers工具类获取，便于在需要的地方直接使用转换功能
    SupplierConverter INSTANCE = Mappers.getMapper(SupplierConverter.class);

    /**
     * 将供应商实体转换为供应商数据对象
     *
     * @param supplier 供应商实体，包含供应商的详细信息
     * @return 转换后的供应商数据对象，用于数据持久化
     */
    @Mapping(source = "supplierCode.value", target = "supplierCode")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "supplierStatus", target = "supplierStatus")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    SupplierDO fromEntity(Supplier supplier);

    /**
     * 将供应商数据对象转换为供应商实体
     *
     * @param supplierDO 供应商数据对象，从数据库中获取的原始数据
     * @return 转换后的供应商实体，用于业务逻辑处理
     */
    @Mapping(target = "supplierCode.value", source = "supplierCode")
    @Mapping(target = "name.value", source = "name")
    @Mapping(target = "supplierStatus", source = "supplierStatus")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    Supplier toEntity(SupplierDO supplierDO);
}
