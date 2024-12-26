package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryProductBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * InquiryProductJpa接口用于处理与InquiryProduct相关的数据库操作。
 * 它继承自ListCrudRepository，从而获得对InquiryProductDO实体进行基本CRUD操作的能力。
 * 此外，该接口还定义了一些特定的查询方法，以满足业务需求。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Repository
public interface InquiryProductJpa extends ListCrudRepository<InquiryProductDO, Long> {
    /**
     * 根据ID和询价编码查找商品。
     *
     * @param id          商品的ID。
     * @param inquiryCode 询价编码。
     * @return 包含指定ID和询价编码的商品的Optional对象，如果未找到则返回空Optional。
     */
    Optional<InquiryProductDO> findByIdAndInquiryCode(Long id, String inquiryCode);

    /**
     * 根据询价编码查询商品基本信息。
     * 此查询返回一个简化的商品视图，仅包含基本字段。
     *
     * @param inquiryCode 询价编码。
     * @return 与指定询价编码相关的一系列商品基本信息的列表。
     */
    @Query("SELECT ip.id as id, ip.inquiryCode as inquiryCode, ip.code as code, ip.name as name, ip.quantity as quantity, ip.units as units, ip.factoryName as factoryName, ip.deliveryDate as deliveryDate" +
            " FROM InquiryProductDO ip WHERE ip.inquiryCode = ?1")
    List<InquiryProductBasic> findProductBasicByInquiryCode(String inquiryCode);

    /**
     * 根据询价编码查找所有商品。
     *
     * @param inquiryCode 询价编码。
     * @return 与指定询价编码相关的一系列商品的列表。
     */
    List<InquiryProductDO> findByInquiryCode(String inquiryCode);

    /**
     * 根据询价编码删除所有商品。
     *
     * @param inquiryCode 询价编码。
     */
    void deleteByInquiryCode(String inquiryCode);
}
