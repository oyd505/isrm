package com.island.isrm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordEncoderTest {

    @Test
    public void encodedPassword() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode("123");
        // {bcrypt}$2a$10$PafSj3FCUjLfqGvkzmTzyOR.tc6NFe8jo3AsV.3OSXR4RxonWBSdq
        // {bcrypt}$2a$10$D3m5GZ2Uoaz2sP4/EwI3dOQ98fB8hbwxQBnQ0egiw.EUUEq0pGcAO
        log.info("password: {}", encodedPassword);
    }
}
