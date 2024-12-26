package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * InquiryProductConverter接口用于在InquiryProduct实体和InquiryProductDO数据对象之间进行转换。
 * 通过@Mapper注解，使用MapStruct生成实现类，以简化和标准化实体与数据对象之间的映射。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Mapper(componentModel = "Spring")
public interface InquiryProductConverter {
    // 使用Mappers工具类获取InquiryProductConverter的实例。
    InquiryProductConverter INSTANCE = Mappers.getMapper(InquiryProductConverter.class);

    /**
     * 将InquiryProduct实体转换为InquiryProductDO数据对象。
     *
     * @param inquiryProduct InquiryProduct实体，包含需要转换的数据。
     * @return 转换后的InquiryProductDO数据对象。
     */
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

    /**
     * 将InquiryProductDO数据对象转换为InquiryProduct实体。
     *
     * @param inquiryProductDO InquiryProductDO数据对象，包含需要转换的数据。
     * @return 转换后的InquiryProduct实体。
     */
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
