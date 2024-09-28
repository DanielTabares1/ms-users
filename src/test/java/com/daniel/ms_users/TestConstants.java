package com.daniel.ms_users;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;

import java.util.Date;

public class TestConstants {

    public static final String NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String DOCUMENT_NUMBER = "123456789";
    public static final String CELLPHONE = "+1234567890";
    public static final String EMAIL = "user@example.com";
    public static final String PASSWORD = "password123";
    public static final Long USER_ID = 1L;
    public static final String ENCODED_PASSWORD = "encodedPassword";


    public static final String ROLE_NAME_ADMIN = "ADMIN";
    public static final String ROLE_NAME_OWNER = "OWNER";
    public static final String ROLE_NAME_USER = "USER";
    public static final String ROLE_NAME_CLIENT = "CLIENT";

    public static final String ROLE_VALUE_OWNER = "ROLE_OWNER";
    public static final String ROLE_VALUE_CLIENT = "ROLE_CLIENT";


    public static final Role ROLE_ADMIN = new Role(1L, ROLE_NAME_ADMIN, "Administrator role");
    public static final Role ROLE_USER = new Role(2L, ROLE_NAME_USER, "User role");
    public static final Role ROLE_OWNER = new Role(1L, ROLE_NAME_OWNER, "Owner role");
    public static final Role ROLE_CLIENT = new Role(4L, ROLE_NAME_CLIENT, "Client role");


    public static final ClientRequest CLIENT_REQUEST = new ClientRequest(
            NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, EMAIL, PASSWORD);


    public static final User USER_OWNER = new User(
            USER_ID, NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, new Date(), EMAIL, PASSWORD, ROLE_OWNER);

    public static final User USER_CLIENT = new User(
            null, NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, new Date(), EMAIL, PASSWORD, ROLE_CLIENT);

    public static final User USER_ADMIN = new User(
            null, NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, new Date(), EMAIL, PASSWORD, ROLE_ADMIN);
}
