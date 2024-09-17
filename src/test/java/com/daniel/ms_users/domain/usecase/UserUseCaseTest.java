package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserReturnsSuccess() {

        Role role = new Role(1L, "OWNER", "El dueño del negocio");

        User user = new User(1L, "John", "Doe", "123456789", "5551234123", new Date(2001, Calendar.OCTOBER,13),
                "john.doe@example.com", "password123", role);

        User savedUser = new User(1L, "John", "Doe", "123456789", "5551234123", new Date(2001, Calendar.OCTOBER,13),
                "john.doe@example.com", "password123", role);

        when(userPersistencePort.saveUser(user)).thenReturn(savedUser);

        User result = userUseCase.saveUser(user);

        verify(userPersistencePort                  , times(1)).saveUser(user);

        assertNotNull(result);
        assertEquals(savedUser, result);

    }

    @Test
    void saveUserThrowsExceptionWhenPersistenceFails() {
        Role role = new Role(1L, "OWNER", "El dueño del negocio");

        User user = new User(1L, "John", "Doe", "123456789", "5551234123",
                new Date(2001 - 1900, Calendar.OCTOBER, 13),
                "john.doe@example.com", "password123", role);

        // Simulamos que el puerto lanza una excepción cuando intenta guardar el usuario
        when(userPersistencePort.saveUser(user)).thenThrow(new RuntimeException("Error de persistencia"));

        // Ejecutamos el método y verificamos que se lanza la excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userUseCase.saveUser(user);
        });

        // Verificamos el mensaje de la excepción
        assertEquals("Error de persistencia", exception.getMessage());

        // Verifica que el puerto de persistencia fue llamado
        verify(userPersistencePort, times(1)).saveUser(user);
    }
}