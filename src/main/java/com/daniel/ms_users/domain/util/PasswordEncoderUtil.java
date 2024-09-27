package com.daniel.ms_users.domain.util;

public interface PasswordEncoderUtil {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
