package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * InquirySupplierConverter接口用于在InquirySupplier实体和InquirySupplierDO数据对象之间进行转换。
 * 该接口使用MapStruct库来实现自动编码生成，以简化转换逻辑。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Mapper(componentModel = "Spring")
public interface InquirySupplierConverter {
    /**
     * INSTANCE是InquirySupplierConverter的实例，通过Mappers工具类获取。
     * 这使得可以在需要的地方直接使用INSTANCE来进行对象转换，而无需每次都创建新的实例。
     */
    InquirySupplierConverter INSTANCE = Mappers.getMapper(InquirySupplierConverter.class);

    /**
     * 将InquirySupplier实体转换为InquirySupplierDO数据对象。
     * 此方法定义了源实体属性到目标数据对象属性的映射关系。
     *
     * @param inquirySupplier InquirySupplier实体，包含需要转换的数据。
     * @return 转换后的InquirySupplierDO数据对象。
     */
    @Mapping(source = "inquiryCode.value", target = "inquiryCode")
    @Mapping(source = "inquirySupplierId.id", target = "id")
    @Mapping(source = "supplier.code.value", target = "code")
    @Mapping(source = "supplier.name", target = "name")
    @Mapping(source = "contact.name", target = "contactName")
    @Mapping(source = "contact.phone", target = "contactPhone")
    @Mapping(source = "confirmed", target = "confirmed")
    @Mapping(source = "audited", target = "audited")
    @Mapping(source = "quoteProgress.quoteCount", target = "quoteCount")
    @Mapping(source = "quoteProgress.quoteCode", target = "quoteCode")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    InquirySupplierDO fromEntity(InquirySupplier inquirySupplier);

    /**
     * 将InquirySupplierDO数据对象转换为InquirySupplier实体。
     * 此方法定义了目标实体属性到源数据对象属性的映射关系。
     *
     * @param inquirySupplierDO InquirySupplierDO数据对象，包含需要转换的数据。
     * @return 转换后的InquirySupplier实体。
     */
    @Mapping(target = "inquiryCode.value", source = "inquiryCode")
    @Mapping(target = "inquirySupplierId.id", source = "id")
    @Mapping(target = "supplier.code.value", source = "code")
    @Mapping(target = "supplier.name", source = "name")
    @Mapping(target = "contact.name", source = "contactName")
    @Mapping(target = "contact.phone", source = "contactPhone")
    @Mapping(target = "confirmed", source = "confirmed")
    @Mapping(target = "audited", source = "audited")
    @Mapping(target = "quoteProgress.quoteCount", source = "quoteCount")
    @Mapping(target = "quoteProgress.quoteCode", source = "quoteCode")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    InquirySupplier toEntity(InquirySupplierDO inquirySupplierDO);
}
