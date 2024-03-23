package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface QuoteRequestConverter {
    QuoteRequestConverter INSTANCE = Mappers.getMapper(QuoteRequestConverter.class);

    @Mapping(source = "quoteCode.value", target = "quoteCode")
    @Mapping(source = "supplier.code.value", target = "supplierCode")
    @Mapping(source = "supplier.name", target = "supplierName")
    @Mapping(source = "amount.value", target = "amount")
    @Mapping(source = "currency.value", target = "currency")
    @Mapping(source = "inquiryCode.value", target = "inquiryCode")
    @Mapping(source = "quoteStatus", target = "status")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    QuoteRequestDO fromEntity(QuoteRequest quoteRequest);

    @Mapping(target = "quoteCode.value", source = "quoteCode")
    @Mapping(target = "supplier.code.value", source = "supplierCode")
    @Mapping(target = "supplier.name", source = "supplierName")
    @Mapping(target = "amount.value", source = "amount")
    @Mapping(target = "currency.value", source = "currency")
    @Mapping(target = "inquiryCode.value", source = "inquiryCode")
    @Mapping(target = "quoteStatus", source = "status")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    QuoteRequest toEntity(QuoteRequestDO quoteRequestDO);
}
