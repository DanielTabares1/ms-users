package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.handler.impl.ClientHandler;
import com.daniel.ms_users.application.mapper.IClientRequestMapper;
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

class ClientHandlerTest {

    @InjectMocks
    private ClientHandler clientHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IClientRequestMapper clientRequestMapper;


    private ClientRequest clientRequest;
    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientRequest = CLIENT_REQUEST;
        user = USER_CLIENT;
    }


    @Test
    void saveClient() {

        when(clientRequestMapper.toModel(any(ClientRequest.class))).thenReturn(user);
        when(userServicePort.saveUser(any(User.class), eq(ROLE_VALUE_CLIENT))).thenReturn(user);

        User result = clientHandler.saveClient(clientRequest);

        verify(clientRequestMapper).toModel(clientRequest);
        verify(userServicePort).saveUser(user, ROLE_VALUE_CLIENT);

        assertEquals(user, result);

    }
}