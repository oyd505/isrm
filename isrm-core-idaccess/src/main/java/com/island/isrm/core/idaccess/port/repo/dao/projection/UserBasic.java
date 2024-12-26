/**
 * 用户基本信息接口
 * 定义了用户基本信息的相关方法，用于获取用户的基本属性
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.idaccess.port.repo.dao.projection;

public interface UserBasic {
    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getUserName();

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    String getNickname();

    /**
     * 获取用户类型
     *
     * @return 用户类型
     */
    String getUserType();

    /**
     * 获取供应商编码
     *
     * @return 供应商编码
     */
    String getSupplierCode();

    /**
     * 获取供应商名称
     *
     * @return 供应商名称
     */
    String getSupplierName();

    /**
     * 获取员工编码
     *
     * @return 员工编码
     */
    String getEmployeeCode();

    /**
     * 获取员工名称
     *
     * @return 员工名称
     */
    String getEmployeeName();

    /**
     * 获取用户角色
     *
     * @return 用户角色
     */
    String getRoles();

    /**
     * 获取用户是否禁用
     *
     * @return 用户是否禁用
     */
    boolean getDisabled();

    /**
     * 获取用户账户是否锁定
     *
     * @return 用户账户是否锁定
     */
    boolean getAccountLocked();
}
