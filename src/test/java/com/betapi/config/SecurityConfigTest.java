package com.betapi.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class SecurityConfigTest {
    private PasswordEncoder passwordEncoder = new BCryptEncoderConfig().passwordEncoder();

    @Test
    void encryptPassword() {
        String password = "root";
        String encoded = passwordEncoder.encode(password);
        System.out.println(encoded);
    }
}