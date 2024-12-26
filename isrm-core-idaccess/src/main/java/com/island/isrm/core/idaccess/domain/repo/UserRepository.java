/**
 * 用户仓储接口，负责用户对象的持久化操作
 * 包括查找、添加、更新和删除用户
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.idaccess.domain.repo;

import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.entity.User;

/**
 * 用户仓储接口，定义了对用户实体进行 CRUD 操作的方法
 */
public interface UserRepository {
    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名，用于唯一标识一个用户
     * @return 返回用户实体，如果找不到则返回 null
     */
    User find(UserName userName);

    /**
     * 向系统中添加新用户
     *
     * @param user 待添加的用户实体
     * @return 返回添加后的用户名，用于确认用户已成功添加
     */
    UserName add(User user);

    /**
     * 更新系统中的用户信息
     *
     * @param user 需要更新的用户实体，包含修改后的信息
     */
    void update(User user);

    /**
     * 从系统中删除用户
     *
     * @param userName 要删除的用户的用户名，用于唯一标识一个用户
     */
    void remove(UserName userName);
}
