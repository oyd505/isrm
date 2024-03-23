package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface QuoteItemConverter {
    QuoteItemConverter INSTANCE = Mappers.getMapper(QuoteItemConverter.class);

    @Mapping(source = "quoteCode.value", target = "quoteCode")
    @Mapping(source = "quoteItemId.id", target = "id")
    @Mapping(source = "inquiryItem.inquiryLine", target = "inquiryLine")
    @Mapping(source = "inquiryItem.productCode", target = "productCode")
    @Mapping(source = "inquiryItem.productName", target = "productName")
    @Mapping(source = "quoteQuantity.value", target = "quoteQuantity")
    @Mapping(source = "quoteQuantity.units", target = "units")
    @Mapping(source = "price.value", target = "price")
    @Mapping(source = "price.taxRate", target = "taxRate")
    @Mapping(source = "deliveryDeadline.date", target = "deliveryDeadline")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    QuoteItemDO fromEntity(QuoteItem quoteItem);

    @Mapping(target = "quoteCode.value", source = "quoteCode")
    @Mapping(target = "quoteItemId.id", source = "id")
    @Mapping(target = "inquiryItem.inquiryLine", source = "inquiryLine")
    @Mapping(target = "inquiryItem.productCode", source = "productCode")
    @Mapping(target = "inquiryItem.productName", source = "productName")
    @Mapping(target = "quoteQuantity.value", source = "quoteQuantity")
    @Mapping(target = "quoteQuantity.units", source = "units")
    @Mapping(target = "price.value", source = "price")
    @Mapping(target = "price.taxRate", source = "taxRate")
    @Mapping(target = "deliveryDeadline.date", source = "deliveryDeadline")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    QuoteItem toEntity(QuoteItemDO quoteItemDO);
}
