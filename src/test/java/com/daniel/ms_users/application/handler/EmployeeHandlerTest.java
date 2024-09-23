package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.handler.impl.EmployeeHandler;
import com.daniel.ms_users.application.mapper.IEmployeeRequestMapper;
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

class EmployeeHandlerTest {

    @InjectMocks
    private EmployeeHandler employeeHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IRoleServicePort roleServicePort;

    @Mock
    private IEmployeeRequestMapper employeeRequestMapper;

    @Mock
    private PasswordEncoderUtil passwordEncoderUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveEmployee() {

        EmployeeRequest request = new EmployeeRequest();

        Role role = new Role(3L, "EMPLOYEE", "El que trabaja en un chuzo");
        User user = new User();
        user.setPassword("encodedPassword");

        when(roleServicePort.getRoleByName("EMPLOYEE")).thenReturn(role);
        when(employeeRequestMapper.toModel(any(EmployeeRequest.class))).thenReturn(user);
        when(passwordEncoderUtil.encode(anyString())).thenReturn("encodedPassword");
        user.setRole(role);
        when(userServicePort.saveUser(any(User.class))).thenReturn(user);

        employeeHandler.saveEmployee(request);

        verify(roleServicePort, times(1)).getRoleByName("EMPLOYEE");
        verify(passwordEncoderUtil, times(1)).encode(user.getPassword());
        verify(employeeRequestMapper, times(1)).toModel(request);
        verify(userServicePort, times(1)).saveUser(user);

        assertEquals("encodedPassword", user.getPassword());
        assertEquals(role, user.getRole());

    }
}