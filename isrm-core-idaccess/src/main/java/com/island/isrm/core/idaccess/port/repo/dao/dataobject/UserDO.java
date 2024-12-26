package com.island.isrm.core.idaccess.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 用户数据对象类，继承自基础数据对象
 * 用于在数据库中表示用户信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class UserDO extends BaseDataObject {
    /**
     * 用户名，作为用户的唯一标识
     */
    @Id
    @Column(nullable = false, length = 32)
    private String userName;

    /**
     * 用户昵称，用于显示在用户界面上
     */
    private String nickname;

    /**
     * 用户密码，用于用户身份验证
     */
    private String password;

    /**
     * 用户类型，用于区分不同类型的用户
     */
    @Column(length = 32)
    private String userType;

    /**
     * 用户角色，用于权限控制
     */
    private String roles;

    /**
     * 供应商编码，用于关联供应商信息
     */
    @Column(length = 32)
    private String supplierCode;

    /**
     * 供应商名称，用于显示供应商信息
     */
    private String supplierName;

    /**
     * 员工编码，用于关联员工信息
     */
    private String employeeCode;

    /**
     * 员工名称，用于显示员工信息
     */
    private String employeeName;

    /**
     * 账户是否禁用，用于控制用户登录权限
     */
    private boolean disabled;

    /**
     * 账户是否锁定，用于安全控制
     */
    private boolean accountLocked;
}
