package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 报价项JPA接口，用于执行与报价项相关的数据库操作
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Repository
public interface QuoteItemJpa extends ListCrudRepository<QuoteItemDO, Long> {
    /**
     * 根据ID和报价单编码查找报价项
     *
     * @param id        报价项的ID
     * @param quoteCode 报价单的编码
     * @return 包含报价项的Optional对象，如果找不到则返回空Optional
     */
    Optional<QuoteItemDO> findByIdAndQuoteCode(Long id, String quoteCode);

    /**
     * 根据报价单编码查找报价项的基本信息
     *
     * @param quoteCode 报价单的编码
     * @return 包含报价项基本信息的列表
     */
    @Query("SELECT qi.id as id, qi.productCode as productCode, qi.productName as productName, qi.price as price, qi.taxRate as taxRate," +
            " qi.quoteCode as quoteCode, qi.deliveryDeadline as deliveryDeadline, qi.quoteQuantity as quoteQuantity, qi.units as units" +
            " FROM QuoteItemDO qi where qi.quoteCode = ?1")
    List<QuoteItemBasic> findItemBasicByQuoteCode(String quoteCode);

    /**
     * 根据报价单编码查找所有的报价项
     *
     * @param quoteCode 报价单的编码
     * @return 包含报价项的列表，如果找不到则返回空列表
     */
    List<QuoteItemDO> findByQuoteCode(String quoteCode);

    /**
     * 根据报价单编码删除所有的报价项
     *
     * @param quoteCode 报价单的编码
     */
    void deleteByQuoteCode(String quoteCode);
}
