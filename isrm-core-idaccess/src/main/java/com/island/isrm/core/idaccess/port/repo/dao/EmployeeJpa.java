package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeCodeAndName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpa extends ListCrudRepository<EmployeeDO, String> {

    /**
     * 查询数据库中最大的员工编码
     *
     * @return 最大的员工编码
     */
    @Query("SELECT MAX(e.employeeCode) FROM EmployeeDO e")
    String findMaxEmployeeCode();

    @Query("SELECT e.employeeCode as employeeCode, e.name as name, e.departmentCode as departmentCode, e.departmentName as departmentName, e.jobTitle as jobTitle, " +
            "e.phone as phone, e.email as email " +
            "FROM EmployeeDO e ORDER BY e.lastModifiedDate DESC ")
    Page<EmployeeBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);

    @Query("SELECT e.employeeCode as code, e.name as name FROM EmployeeDO e")
    List<EmployeeCodeAndName> listCodeAndName();
}
