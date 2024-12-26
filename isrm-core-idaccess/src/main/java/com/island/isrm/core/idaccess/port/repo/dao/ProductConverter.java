package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.domain.entity.Product;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.ProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ProductConverter接口用于在Product实体和ProductDO数据对象之间进行转换。
 * 它使用MapStruct库来自动映射这两个类之间的属性。
 * MapStruct是一个编码生成器，它简化了将Java对象映射到其他Java对象的过程。
 * 通过在接口中定义映射方法，MapStruct会自动生成实现类，从而避免手动编写转换逻辑。
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Mapper(componentModel = "Spring")
public interface ProductConverter {
    /**
     * ProductConverter的实例，通过Mappers工具类获取。
     * 这使得可以在任何需要的地方直接使用ProductConverter进行对象转换，而无需手动实例化它。
     */
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    /**
     * 将Product实体转换为ProductDO数据对象。
     * 此方法定义了源属性和目标属性之间的映射关系，包括嵌套属性和特定字段的映射。
     *
     * @param product 要转换的Product实体。
     * @return 转换后的ProductDO数据对象。
     */
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

    /**
     * 将ProductDO数据对象转换为Product实体。
     * 此方法的映射注解定义了与fromEntity方法相反的转换方向，
     * 将数据对象的属性映射回实体的属性。
     *
     * @param productDO 要转换的ProductDO数据对象。
     * @return 转换后的Product实体。
     */
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
