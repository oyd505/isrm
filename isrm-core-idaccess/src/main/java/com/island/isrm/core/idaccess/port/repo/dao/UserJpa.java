package com.island.isrm.core.idaccess.port.repo.dao;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户信息持久化接口
 * 提供用户基本信息的查询功能，特别是按照最后修改时间降序查询用户列表
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Repository
public interface UserJpa extends ListCrudRepository<UserDO, String> {

    /**
     * 查询用户基本信息，并按照最后修改时间降序排序
     *
     * @param pageable 分页信息，用于指定查询的页数和每页大小
     * @return 包含用户基本信息的分页查询结果
     */
    @Query("SELECT u.userName as userName, u.nickname as nickname, u.userType as userType, u.roles as roles, u.disabled as disabled, u.accountLocked as accountLocked " +
            " , u.supplierCode as supplierCode, u.supplierName as supplierName ,u.employeeCode as employeeCode, u.employeeName as employeeName " +
            "FROM UserDO u ORDER BY u.lastModifiedDate DESC ")
    Page<UserBasic> findBasicOrderByLastModifiedDateDesc(Pageable pageable);
}
