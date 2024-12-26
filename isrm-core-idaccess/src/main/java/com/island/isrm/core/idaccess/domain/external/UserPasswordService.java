package com.island.isrm.core.idaccess.domain.external;

import com.island.isrm.core.common.domain.dp.Password;

/**
 * 用户密码服务
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface UserPasswordService {
    
    /**
     * 生成一个新密码
     * 
     * 此方法用于生成一个新的、安全的密码通常用于用户注册或密码重置功能
     * 
     * @return 生成的密码对象，包含加密后的密码及其相关信息
     */
    Password generatePassword();

    /**
     * 加密明文密码
     * 
     * 此方法接受一个明文密码字符串，将其加密并返回加密后的密码对象
     * 加密过程应是单向的，确保即使数据库泄露，用户密码也不会直接暴露
     * 
     * @param password 明文密码字符串
     * @return 加密后的密码对象
     */
    Password encodePassword(String password);

    /**
     * 更新用户密码
     * 
     * 此方法用于验证旧密码并设置新密码用户必须提供当前密码和新密码
     * 系统将验证当前密码是否正确，如果正确，则将密码更新为新密码
     * 
     * @param oldPassword 用户当前的密码
     * @param newPassword 用户希望设置的新密码
     * @param encodedPassword 当前存储在系统中的加密密码，用于验证旧密码
     * @return 更新后的密码对象
     */
    Password updatePassword(String oldPassword, String newPassword, Password encodedPassword);
}
