package com.daniel.ms_users.infrastructure.security;

import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoderUtil {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
