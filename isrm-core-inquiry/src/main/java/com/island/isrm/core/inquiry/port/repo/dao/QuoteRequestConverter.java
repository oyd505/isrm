package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 报价请求转换器接口，用于在报价请求实体和数据对象之间进行转换
 * 该接口使用MapStruct框架来实现自动转换
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Mapper(componentModel = "Spring")
public interface QuoteRequestConverter {
    /**
     * 获取报价请求转换器实例
     *
     * @return 报价请求转换器实例
     */
    QuoteRequestConverter INSTANCE = Mappers.getMapper(QuoteRequestConverter.class);

    /**
     * 将报价请求实体转换为数据对象
     *
     * @param quoteRequest 报价请求实体，包含报价的详细信息
     * @return 报价请求数据对象，用于数据库操作
     */
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

    /**
     * 将报价请求数据对象转换为实体
     *
     * @param quoteRequestDO 报价请求数据对象，包含从数据库查询的数据
     * @return 报价请求实体，用于业务逻辑处理
     */
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
