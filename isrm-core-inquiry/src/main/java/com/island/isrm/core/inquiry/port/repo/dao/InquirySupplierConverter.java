package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface InquirySupplierConverter {
    InquirySupplierConverter INSTANCE = Mappers.getMapper(InquirySupplierConverter.class);

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
