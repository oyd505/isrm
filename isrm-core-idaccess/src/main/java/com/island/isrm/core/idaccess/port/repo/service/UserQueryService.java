package com.island.isrm.core.idaccess.port.repo.service;

import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.port.repo.dao.UserJpa;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import com.island.isrm.core.idaccess.port.repo.service.dto.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 用户查询服务类，提供用户相关的查询功能
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class UserQueryService {
    // 用户数据访问对象，用于操作用户数据
    private final UserJpa userJpa;

    /**
     * 构造函数，初始化UserQueryService
     *
     * @param userJpa 用户数据访问对象
     */
    public UserQueryService(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    /**
     * 根据用户名查找用户详细信息
     *
     * @param userName 用户名
     * @return 用户详细信息对象，如果找不到则返回一个新的UserDO对象
     */
    public UserDO find(String userName) {
        return userJpa.findById(userName).orElse(new UserDO());
    }

    /**
     * 分页查询用户基本信息，按最后修改日期降序排列
     *
     * @param pageable 分页参数
     * @return 用户基本信息的分页结果
     */
    public Page<UserBasic> pageBasic(Pageable pageable) {
        return userJpa.findBasicOrderByLastModifiedDateDesc(pageable);
    }

    /**
     * 根据用户名查找登录用户信息，包括昵称和用户类型等
     *
     * @param userName 用户名
     * @return 登录用户信息对象
     */
    public LoginUser findLoginUser(String userName) {
        // 首先查找用户详细信息
        UserDO userDO = find(userName);
        LoginUser loginUser = new LoginUser();
        // 设置登录用户的用户名、昵称和用户类型
        loginUser.setUserName(userName);
        loginUser.setNickname(userDO.getNickname());
        loginUser.setUserType(userDO.getUserType());
        // 如果用户类型为外部用户，则设置用户供应商信息
        if (UserType.valueOf(userDO.getUserType()).isExternal()) {
            loginUser.setUserSupplier(new UserSupplier(userDO.getSupplierCode(), userDO.getSupplierName()));
        }
        return loginUser;
    }
}
