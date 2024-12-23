package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.port.repo.dao.EmployeeJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeCodeAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeQueryService {

    private final EmployeeJpa employeeJpa;

    @Autowired
    public EmployeeQueryService(EmployeeJpa employeeJpa) {
        this.employeeJpa = employeeJpa;
    }

    public EmployeeDO find(String employeeCode) {
        return employeeJpa.findById(employeeCode).orElse(new EmployeeDO());
    }

    public Page<EmployeeBasic> pageBasic(Pageable pageable) {
        return employeeJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    public List<EmployeeCodeAndName> listCodeAndName() {
        return employeeJpa.listCodeAndName();
    }
}
