package com.island.isrm.core.idaccess.port.external;

import com.island.isrm.core.common.domain.dp.Password;
import com.island.isrm.core.idaccess.domain.external.UserPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordServiceImpl implements UserPasswordService {
    private final PasswordEncoder passwordEncoder;

    public UserPasswordServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Password generatePassword() {
        return this.encodePassword("123");
    }

    @Override
    public Password encodePassword(String password) {
        return new Password(this.passwordEncoder.encode(password));
    }

    @Override
    public Password updatePassword(String oldPassword, String newPassword, Password encodedPassword) {
        if (!this.passwordEncoder.matches(oldPassword, encodedPassword.getValue())) {
            throw new IllegalArgumentException("旧密码不正确");
        }
        return this.encodePassword(newPassword);
    }
}
