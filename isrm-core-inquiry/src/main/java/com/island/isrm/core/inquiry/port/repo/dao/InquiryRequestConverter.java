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

/**
 * 询价请求转换器接口，用于在实体、数据访问对象和传输对象之间进行映射。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Mapper(componentModel = "Spring")
public interface InquiryRequestConverter {
    /**
     * 获取询价请求转换器实例。
     */
    InquiryRequestConverter INSTANCE = Mappers.getMapper(InquiryRequestConverter.class);

    /**
     * 将询价请求实体转换为数据访问对象。
     *
     * @param inquiryRequest 询价请求实体。
     * @return 询价请求数据访问对象。
     */
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

    /**
     * 将询价请求数据访问对象转换为实体。
     *
     * @param inquiryRequestDO 询价请求数据访问对象。
     * @return 询价请求实体。
     */
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

    /**
     * 将询价请求数据访问对象、供应商数据访问对象和商品数据访问对象列表转换为传输对象。
     *
     * @param inquiryRequestDO  询价请求数据访问对象。
     * @param inquirySupplierDO 供应商数据访问对象。
     * @param productDOList     商品数据访问对象列表。
     * @return 询价供应商商品传输对象。
     */
    @Mapping(source = "inquiryRequestDO.inquiryCode", target = "inquiryCode")
    @Mapping(source = "inquiryRequestDO.status", target = "status")
    @Mapping(source = "inquirySupplierDO", target = "supplier")
    @Mapping(source = "productDOList", target = "products")
    InquirySupplierProducts toDto(InquiryRequestDO inquiryRequestDO, InquirySupplierDO inquirySupplierDO, List<InquiryProductDO> productDOList);
}
