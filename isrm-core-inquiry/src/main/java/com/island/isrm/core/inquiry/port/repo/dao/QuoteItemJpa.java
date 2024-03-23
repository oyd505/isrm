package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteItemJpa extends ListCrudRepository<QuoteItemDO, Long> {
    Optional<QuoteItemDO> findByIdAndQuoteCode(Long id, String quoteCode);

    @Query("SELECT qi.id as id, qi.productCode as productCode, qi.productName as productName, qi.price as price, qi.taxRate as taxRate," +
            " qi.quoteCode as quoteCode, qi.deliveryDeadline as deliveryDeadline, qi.quoteQuantity as quoteQuantity, qi.units as units" +
            " FROM QuoteItemDO qi where qi.quoteCode = ?1")
    List<QuoteItemBasic> findItemBasicByQuoteCode(String quoteCode);

    List<QuoteItemDO> findByQuoteCode(String quoteCode);

    void deleteByQuoteCode(String quoteCode);
}
