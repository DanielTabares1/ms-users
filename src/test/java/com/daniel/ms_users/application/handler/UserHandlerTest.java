package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.handler.impl.UserHandler;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static com.daniel.ms_users.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_ShouldReturnUser() {
        when(userServicePort.getUserById(anyLong())).thenReturn(USER_CLIENT);

        User result = userHandler.getUserById(USER_ID);

        verify(userServicePort).getUserById(USER_ID);

        assertEquals(USER_CLIENT, result);
    }

    @Test
    void getUserByEmail_ShouldReturnUser() {
        when(userServicePort.getUserByEmail(anyString())).thenReturn(USER_CLIENT);

        User result = userHandler.getUserByEmail(EMAIL);

        verify(userServicePort).getUserByEmail(EMAIL);

        assertEquals(USER_CLIENT, result);
    }
}