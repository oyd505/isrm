package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface InquiryProductConverter {
    InquiryProductConverter INSTANCE = Mappers.getMapper(InquiryProductConverter.class);

    @Mapping(source = "inquiryCode.value", target = "inquiryCode")
    @Mapping(source = "inquiryProductId.id", target = "id")
    @Mapping(source = "product.code", target = "code")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "quantity.value", target = "quantity")
    @Mapping(source = "quantity.units", target = "units")
    @Mapping(source = "factoryOrg.code", target = "factoryCode")
    @Mapping(source = "factoryOrg.name", target = "factoryName")
    @Mapping(source = "deliveryDate.date", target = "deliveryDate")
    @Mapping(source = "lineNumber.index", target = "lineNumber")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    InquiryProductDO fromEntity(InquiryProduct inquiryProduct);

    @Mapping(target = "inquiryCode.value", source = "inquiryCode")
    @Mapping(target = "inquiryProductId.id", source = "id")
    @Mapping(target = "product.code", source = "code")
    @Mapping(target = "product.name", source = "name")
    @Mapping(target = "quantity.value", source = "quantity")
    @Mapping(target = "quantity.units", source = "units")
    @Mapping(target = "factoryOrg.code", source = "factoryCode")
    @Mapping(target = "factoryOrg.name", source = "factoryName")
    @Mapping(target = "deliveryDate.date", source = "deliveryDate")
    @Mapping(target = "lineNumber.index", source = "lineNumber")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    InquiryProduct toEntity(InquiryProductDO inquiryProductDO);
}
