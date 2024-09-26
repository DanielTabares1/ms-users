package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.domain.exception.UserUnderageException;
import com.daniel.ms_users.application.handler.impl.OwnerHandler;
import com.daniel.ms_users.application.mapper.IOwnerRequestMapper;
import com.daniel.ms_users.domain.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.util.UserValidations;
import com.daniel.ms_users.domain.api.IRoleServicePort;
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

class OwnerHandlerTest {

    @InjectMocks
    private OwnerHandler ownerHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IRoleServicePort roleServicePort;

    @Mock
    private IOwnerRequestMapper IOwnerRequestMapper;

    @Mock
    private UserValidations userValidations;

    @Mock
    private PasswordEncoderUtil passwordEncoderUtil;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOwner() {
        OwnerRequest ownerRequest = new OwnerRequest("Daniel", "Tabares", "1007480705", "3222574446",
                new Date(2001, Calendar.OCTOBER, 13), "daniel.tabares@pragma.com.co", "password");

        Role ownerRole = new Role(1L, "OWNER", "El dueño de un negocio");
        User user = new User(null, "Daniel", "Tabares", "1007480705", "3222574446",
                new Date(2001 , Calendar.OCTOBER, 13), "daniel.tabares@pragma.com.co", "encodedPassword", ownerRole);

        // Mockear comportamientos
        when(roleServicePort.getRoleByName("OWNER")).thenReturn(ownerRole); // Obtener el rol OWNER
        when(passwordEncoderUtil.encode(user.getPassword())).thenReturn("encodedPassword"); // Simula el encriptado de la contraseña
        when(IOwnerRequestMapper.toUser(ownerRequest)).thenReturn(user); // Convertir el OwnerRequest a User
        when(userValidations.isAdult(user)).thenReturn(true); // Validaciones del usuario (si no lanza excepciones)
        when(userServicePort.saveUser(user)).thenReturn(user);

        // Ejecutar el método a probar
        ownerHandler.saveOwner(ownerRequest);

        // Verificaciones
        verify(roleServicePort, times(1)).getRoleByName("OWNER");
        verify(passwordEncoderUtil, times(1)).encode(user.getPassword());
        verify(IOwnerRequestMapper, times(1)).toUser(ownerRequest);
        verify(userValidations, times(1)).isAdult(user);
        verify(userServicePort, times(1)).saveUser(user);

        // Verificar que el role y la password se setearon correctamente en el User
        assertEquals("encodedPassword", user.getPassword());
        assertEquals(ownerRole, user.getRole());
    }

    @Test
    void saveOwnerThrowsUserUnderageException() {
        OwnerRequest ownerRequest = new OwnerRequest("Daniel", "Tabares", "1007480705", "3222574446",
                new Date(2008, Calendar.OCTOBER, 13), "daniel.tabares@pragma.com.co", "password");

        Role ownerRole = new Role(1L, "OWNER", "El dueño de un negocio");
        User user = new User(null, "Daniel", "Tabares", "1007480705", "3222574446",
                new Date(2008 , Calendar.OCTOBER, 13), "daniel.tabares@pragma.com.co", "encodedPassword", ownerRole);

        // Mockear comportamientos
        when(roleServicePort.getRoleByName("OWNER")).thenReturn(ownerRole); // Obtener el rol OWNER
        when(passwordEncoderUtil.encode(user.getPassword())).thenReturn("encodedPassword"); // Simula el encriptado de la contraseña
        when(IOwnerRequestMapper.toUser(ownerRequest)).thenReturn(user); // Convertir el OwnerRequest a User
        when(userValidations.isAdult(user)).thenReturn(false); // Simular que el usuario no es adulto


        // Ejecutar el método a probar y capturar la excepción
        assertThrows(UserUnderageException.class, () -> {
            ownerHandler.saveOwner(ownerRequest);
        });

        // Verificaciones
        verify(roleServicePort, times(1)).getRoleByName("OWNER");
        verify(IOwnerRequestMapper, times(1)).toUser(ownerRequest);
        verify(userValidations, times(1)).isAdult(user);
        verify(passwordEncoderUtil, never()).encode(user.getPassword());
        verify(userServicePort, never()).saveUser(user); // Verificar que nunca se guarda el usuario, ya que es menor de edad
    }
}