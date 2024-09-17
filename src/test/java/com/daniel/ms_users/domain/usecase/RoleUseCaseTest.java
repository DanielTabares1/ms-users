package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.infrastructure.exception.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoleUseCaseTest {

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Mock
    IRolePersistencePort rolePersistencePort;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoleByNameReturnsSuccess() {
        //Wanted result for opperation
        Role role = new Role(4, "OWNER", "El dueño de un chuzo");

        when(rolePersistencePort.getRoleByName("OWNER")).thenReturn(role);

        Role roleResult = roleUseCase.getRoleByName("OWNER");

        assertNotNull(roleResult);
        assertEquals("OWNER", roleResult.getName());

        verify(rolePersistencePort, times(1)).getRoleByName("OWNER");
    }

    @Test
    void getRoleByNameReturnsRoleNotFoundException(){

        when(rolePersistencePort.getRoleByName("OWNER")).thenThrow(new RoleNotFoundException());


        assertThrows(RoleNotFoundException.class, () -> roleUseCase.getRoleByName("OWNER"));


        verify(rolePersistencePort, times(1)).getRoleByName("OWNER");
    }
}