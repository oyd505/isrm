package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 报价项实体与数据对象相互转换的映射接口
 * <p>
 * 该接口使用MapStruct框架来实现实体类和数据访问层对象之间的相互转换
 * MapStruct能够自动生成转换实现类，简化了转换逻辑的编写
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Mapper(componentModel = "Spring")
public interface QuoteItemConverter {
    // 获取MapStruct自动生成的转换实现类实例
    QuoteItemConverter INSTANCE = Mappers.getMapper(QuoteItemConverter.class);

    /**
     * 将报价项实体转换为数据对象
     *
     * @param quoteItem 报价项实体，包含报价项的各种属性信息
     * @return 转换后的报价项数据对象，用于数据持久化
     */
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

    /**
     * 将报价项数据对象转换为实体
     *
     * @param quoteItemDO 报价项数据对象，从数据库中获取的数据
     * @return 转换后的报价项实体，用于业务逻辑处理
     */
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
