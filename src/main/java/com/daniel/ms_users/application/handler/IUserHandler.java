package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.domain.model.User;

public interface IUserHandler {
    User getUserById(Long id);

    User getUserByEmail(String email);
}
