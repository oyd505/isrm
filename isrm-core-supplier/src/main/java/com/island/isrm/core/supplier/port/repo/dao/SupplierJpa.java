package com.island.isrm.core.supplier.port.repo.dao;

import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierBasic;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierJpa extends ListCrudRepository<SupplierDO, String> {

    @Query("select max(s.supplierCode) from SupplierDO s")
    String findMaxSupplierCode();

    @Query("SELECT s.supplierCode as supplierCode, s.name as name, s.supplierStatus as supplierStatus" +
            " FROM SupplierDO s ORDER BY s.lastModifiedDate DESC ")
    Page<SupplierBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    @Query("SELECT s.supplierCode as code, s.name as name FROM SupplierDO s")
    List<SupplierCodeAndName> listCodeAndName();
}
