package com.island.isrm.core.idaccess.port.external;

import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.idaccess.domain.external.UserPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户密码服务实现类
 * 提供密码生成、编码和更新的服务
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class UserPasswordServiceImpl implements UserPasswordService {
    // 用于密码编码的编码器
    private final PasswordEncoder passwordEncoder;

    /**
     * 构造函数注入PasswordEncoder
     *
     * @param passwordEncoder 密码编码器
     */
    public UserPasswordServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 生成默认密码
     * 此方法主要用于创建新用户时生成一个默认的编码后的密码
     *
     * @return 编码后的默认密码
     */
    @Override
    public Password generatePassword() {
        return this.encodePassword("123");
    }

    /**
     * 对明文密码进行编码
     * 此方法用于将明文密码转换为编码后的密码，以保证密码的安全性
     *
     * @param password 明文密码
     * @return 编码后的密码
     */
    @Override
    public Password encodePassword(String password) {
        return new Password(this.passwordEncoder.encode(password));
    }

    /**
     * 更新用户密码
     * 此方法用于验证旧密码并设置新密码，确保只有知道当前密码的用户才能更改密码
     *
     * @param oldPassword     用户输入的旧密码
     * @param newPassword     用户想要设置的新密码
     * @param encodedPassword 数据库中存储的编码后的旧密码
     * @return 编码后的新密码
     * @throws IllegalArgumentException 如果旧密码不匹配，则抛出此异常
     */
    @Override
    public Password updatePassword(String oldPassword, String newPassword, Password encodedPassword) {
        if (!this.passwordEncoder.matches(oldPassword, encodedPassword.getValue())) {
            throw new IllegalArgumentException("旧密码不正确");
        }
        return this.encodePassword(newPassword);
    }
}
