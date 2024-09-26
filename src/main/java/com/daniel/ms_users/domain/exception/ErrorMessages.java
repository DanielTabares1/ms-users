package com.daniel.ms_users.domain.exception;

public enum ErrorMessages {
    USER_NOT_FOUND("User not found with ID: %d"),
    USER_NOT_FOUND_BY_EMAIL("User not found with Email: %s"),
    ROLE_NOT_FOUND("Role not found with name: %s"),
    EMAIL_ALREADY_IN_USE("User with email: %s is already in use by another user."),
    USER_UNDERAGE("User is underage.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
