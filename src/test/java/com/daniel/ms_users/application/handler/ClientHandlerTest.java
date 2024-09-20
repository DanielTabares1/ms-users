package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.mapper.IClientRequestMapper;
import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientHandlerTest {

    @InjectMocks
    private ClientHandler clientHandler;

    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private IRoleServicePort roleServicePort;
    @Mock
    private IClientRequestMapper clientRequestMapper;
    @Mock
    private PasswordEncoderUtil passwordEncoderUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient() {

        ClientRequest request = new ClientRequest();

        Role role = new Role(4L, "CLIENT", "El que traga");
        User user = new User();
        user.setPassword("encodedPassword");

        when(roleServicePort.getRoleByName("CLIENT")).thenReturn(role);
        when(clientRequestMapper.toModel(any(ClientRequest.class))).thenReturn(user);
        when(passwordEncoderUtil.encode(anyString())).thenReturn("encodedPassword");
        user.setRole(role);
        when(userServicePort.saveUser(any(User.class))).thenReturn(user);

        clientHandler.saveClient(request);

        verify(roleServicePort, times(1)).getRoleByName("CLIENT");
        verify(passwordEncoderUtil, times(1)).encode(user.getPassword());
        verify(clientRequestMapper, times(1)).toModel(request);
        verify(userServicePort, times(1)).saveUser(user);

        assertEquals("encodedPassword", user.getPassword());
        assertEquals(role, user.getRole());

    }
}