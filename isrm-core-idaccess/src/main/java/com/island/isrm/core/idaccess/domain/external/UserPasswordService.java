package com.island.isrm.core.idaccess.domain.external;

import com.island.isrm.core.common.domain.dp.Password;

/**
 * 用户密码服务
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface UserPasswordService {

    Password generatePassword();

    Password encodePassword(String password);

    Password updatePassword(String oldPassword, String newPassword, Password encodedPassword);
}
