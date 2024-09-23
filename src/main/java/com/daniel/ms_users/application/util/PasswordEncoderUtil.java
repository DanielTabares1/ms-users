package com.daniel.ms_users.application.util;

public interface PasswordEncoderUtil {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
