package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.Product;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface ProductConverter {
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    @Mapping(source = "productCode.value", target = "productCode")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "price.value", target = "price")
    @Mapping(source = "price.taxRate", target = "taxRate")
    @Mapping(source = "unit.name", target = "unitName")
    @Mapping(source = "unit.symbol", target = "unitSymbol")
    @Mapping(source = "category.code", target = "categoryCode")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "version.value", target = "version")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdDate.time", target = "createdDate")
    ProductDO fromEntity(Product product);

    @Mapping(target = "productCode.value", source = "productCode")
    @Mapping(target = "name.value", source = "name")
    @Mapping(target = "price.value", source = "price")
    @Mapping(target = "price.taxRate", source = "taxRate")
    @Mapping(target = "unit.name", source = "unitName")
    @Mapping(target = "unit.symbol", source = "unitSymbol")
    @Mapping(target = "category.code", source = "categoryCode")
    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "version.value", source = "version")
    @Mapping(target = "createdBy.id", source = "createdBy")
    @Mapping(target = "createdDate.time", source = "createdDate")
    Product toEntity(ProductDO productDO);
} 