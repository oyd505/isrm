package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpa extends ListCrudRepository<UserDO, String> {

    @Query("SELECT u.userName as userName, u.nickname as nickname, u.userType as userType, u.roles as roles, u.disabled as disabled, u.accountLocked as accountLocked " +
            " , u.supplierCode as supplierCode, u.supplierName as supplierName ,u.employeeCode as employeeCode, u.employeeName as employeeName " +
            "FROM UserDO u ORDER BY u.lastModifiedDate DESC ")
    Page<UserBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);
}
