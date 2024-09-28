package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.TestConstants;
import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.UserRoles;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.exception.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.daniel.ms_users.TestConstants.ROLE_ADMIN;
import static com.daniel.ms_users.TestConstants.ROLE_NAME_ADMIN;
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

        when(rolePersistencePort.getRoleByName(ROLE_NAME_ADMIN)).thenReturn(Optional.of(ROLE_ADMIN));

        Role roleResult = roleUseCase.getRoleByName(ROLE_NAME_ADMIN);


        assertNotNull(roleResult);
        assertEquals(ROLE_NAME_ADMIN, roleResult.getName());
        verify(rolePersistencePort, times(1)).getRoleByName(ROLE_NAME_ADMIN);
    }

    @Test
    void getRoleByNameReturnsRoleNotFoundException(){

        when(rolePersistencePort.getRoleByName(ROLE_NAME_ADMIN)).thenReturn(Optional.empty());


        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class,
                () -> roleUseCase.getRoleByName(ROLE_NAME_ADMIN));

        assertEquals(ErrorMessages.ROLE_NOT_FOUND.getMessage(ROLE_NAME_ADMIN), exception.getMessage());
        verify(rolePersistencePort, times(1)).getRoleByName(ROLE_NAME_ADMIN);
    }
}