package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.handler.impl.EmployeeHandler;
import com.daniel.ms_users.application.mapper.IEmployeeRequestMapper;
import com.daniel.ms_users.domain.model.UserRoles;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.daniel.ms_users.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeHandlerTest {

    @InjectMocks
    private EmployeeHandler employeeHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IEmployeeRequestMapper employeeRequestMapper;


    private EmployeeRequest employeeRequest;
    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeRequest = new EmployeeRequest(NAME, LAST_NAME, DOCUMENT_NUMBER, CELLPHONE, EMAIL, PASSWORD);
        user = USER_CLIENT;
    }


    @Test
    void saveEmployee() {

        when(employeeRequestMapper.toModel(any(EmployeeRequest.class))).thenReturn(user);
        when(userServicePort.saveUser(any(User.class), eq(UserRoles.EMPLOYEE.toString()))).thenReturn(user);

        User result = employeeHandler.saveEmployee(employeeRequest);

        verify(employeeRequestMapper).toModel(employeeRequest);
        verify(userServicePort).saveUser(user, UserRoles.EMPLOYEE.toString());

        assertEquals(user, result);

    }
}