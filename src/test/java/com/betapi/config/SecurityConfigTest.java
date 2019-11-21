package com.betapi.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
class SecurityConfigTest {
    private PasswordEncoder passwordEncoder = new BCryptEncoderConfig().passwordEncoder();

    @Test
    void encryptPassword() {
        passwordEncoder.encode("test");
    }
}