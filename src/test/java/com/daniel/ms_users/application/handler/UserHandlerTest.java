package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.handler.impl.UserHandler;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserByIdReturnsSuccess() {
       User user = new User(
               7L,
               "Daniel",
               "Tabares",
               "1007480705",
               "+3222574446",
               new Date(2001, Calendar.OCTOBER,13),
               "daniel.tabares@pragma.com.co",
               "$2a$10$rKPEB3J.6k5hpbxWnxQFvuJU8XU0E0OZs9sOhIux08XkiXUnUwfTO",
               new Role(3l, "ADMIN", "El dueño de todo")
       );

       when(userServicePort.getUserById(7L)).thenReturn(user);

       User result = userHandler.getUserById(7L);

       assertNotNull(result);
       assertEquals(result.getId(), user.getId());
       assertEquals(result.getRole(), user.getRole());
       assertEquals(result.getEmail(), user.getEmail());

       verify(userServicePort, times(1)).getUserById(7L);
    }
}