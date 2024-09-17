package com.daniel.ms_users.infrastructure.output.jpa.adapter;

import com.daniel.ms_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Test
    void saveUserReturnsSuccess() {

    }
}