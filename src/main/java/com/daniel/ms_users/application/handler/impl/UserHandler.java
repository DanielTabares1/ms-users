package com.daniel.ms_users.application.handler.impl;

import com.daniel.ms_users.application.handler.IUserHandler;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;

    @Override
    public User getUserById(Long id) {
        return userServicePort.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userServicePort.getUserByEmail(email);
    }
}
