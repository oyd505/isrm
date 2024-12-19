package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.ContactNameAndPhone;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierContactBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierContactJpa extends ListCrudRepository<SupplierContactDO, Long> {

    Optional<SupplierContactDO> findByIdAndSupplierCode(Long id, String supplierCode);

    @Query("SELECT sc.id as id, sc.name as name, sc.phone as phone, sc.supplierCode as supplierCode " +
            " FROM SupplierContactDO sc WHERE sc.supplierCode = ?1")
    List<SupplierContactBasic> findContactBasicBySupplierCode(String supplierCode);

    @Query("SELECT sc.name as name, sc.phone as phone " +
            " FROM SupplierContactDO sc WHERE sc.supplierCode = ?1")
    List<ContactNameAndPhone> listContactNameAndPhone(String supplierCode);

    void deleteBySupplierCode(String supplierCode);
}
