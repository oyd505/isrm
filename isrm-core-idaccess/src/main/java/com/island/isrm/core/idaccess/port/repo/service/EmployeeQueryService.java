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

/**
 * 员工查询服务类，提供员工信息的查询接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class EmployeeQueryService {

    // 员工数据访问对象，用于操作数据库中的员工信息
    private final EmployeeJpa employeeJpa;

    /**
     * 构造函数注入EmployeeJpa
     *
     * @param employeeJpa 员工数据访问对象
     */
    @Autowired
    public EmployeeQueryService(EmployeeJpa employeeJpa) {
        this.employeeJpa = employeeJpa;
    }

    /**
     * 根据员工编码查找员工详细信息
     *
     * @param employeeCode 员工编码
     * @return 员工详细信息对象，如果找不到则返回空的EmployeeDO对象
     */
    public EmployeeDO find(String employeeCode) {
        return employeeJpa.findById(employeeCode).orElse(new EmployeeDO());
    }

    /**
     * 分页查询员工基本信息，结果按最后修改日期降序排列
     *
     * @param pageable 分页参数
     * @return 员工基本信息的分页结果
     */
    public Page<EmployeeBasic> pageBasic(Pageable pageable) {
        return employeeJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    /**
     * 查询所有员工的编码和名称
     *
     * @return 员工编码和名称的列表
     */
    public List<EmployeeCodeAndName> listCodeAndName() {
        return employeeJpa.listCodeAndName();
    }
}
