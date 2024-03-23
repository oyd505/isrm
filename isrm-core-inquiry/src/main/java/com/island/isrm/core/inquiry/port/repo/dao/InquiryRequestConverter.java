package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.service.dto.InquirySupplierProducts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface InquiryRequestConverter {
    InquiryRequestConverter INSTANCE = Mappers.getMapper(InquiryRequestConverter.class);

    @Mapping(source = "inquiryCode.value", target = "inquiryCode")
    @Mapping(source = "title.value", target = "title")
    @Mapping(source = "reason.value", target = "reason")
    @Mapping(source = "participation", target = "participation")
    @Mapping(source = "buyer.code", target = "buyerCode")
    @Mapping(source = "buyer.name", target = "buyerName")
    @Mapping(source = "inquiryStatus", target = "status")
    @Mapping(source = "quoteTime.startTime", target = "startTime")
    @Mapping(source = "quoteTime.endTime", target = "endTime")
    @Mapping(source = "paymentTerms.value", target = "paymentTerms")
    @Mapping(source = "currency.value", target = "currency")
    @Mapping(source = "purchasingOrg.code", target = "purOrgCode")
    @Mapping(source = "purchasingOrg.name", target = "purOrgName")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    InquiryRequestDO fromEntity(InquiryRequest inquiryRequest);

    @Mapping(target = "inquiryCode.value", source = "inquiryCode")
    @Mapping(target = "title.value", source = "title")
    @Mapping(target = "reason.value", source = "reason")
    @Mapping(target = "participation", source = "participation")
    @Mapping(target = "buyer.code", source = "buyerCode")
    @Mapping(target = "buyer.name", source = "buyerName")
    @Mapping(target = "inquiryStatus", source = "status")
    @Mapping(target = "quoteTime.startTime", source = "startTime")
    @Mapping(target = "quoteTime.endTime", source = "endTime")
    @Mapping(target = "paymentTerms.value", source = "paymentTerms")
    @Mapping(target = "currency.value", source = "currency")
    @Mapping(target = "purchasingOrg.code", source = "purOrgCode")
    @Mapping(target = "purchasingOrg.name", source = "purOrgName")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    InquiryRequest toEntity(InquiryRequestDO inquiryRequestDO);

    @Mapping(source = "inquiryRequestDO.inquiryCode", target = "inquiryCode")
    @Mapping(source = "inquiryRequestDO.status", target = "status")
    @Mapping(source = "inquirySupplierDO", target = "supplier")
    @Mapping(source = "productDOList", target = "products")
    InquirySupplierProducts toDto(InquiryRequestDO inquiryRequestDO, InquirySupplierDO inquirySupplierDO, List<InquiryProductDO> productDOList);
}
