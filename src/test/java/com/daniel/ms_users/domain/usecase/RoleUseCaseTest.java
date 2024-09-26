package com.daniel.ms_users.domain.usecase;

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
        Role role = new Role();
        String roleName = UserRoles.OWNER.name();
        role.setName(roleName);

        when(rolePersistencePort.getRoleByName(anyString())).thenReturn(role);

        Role roleResult = roleUseCase.getRoleByName(roleName);

        assertNotNull(roleResult);
        assertEquals("OWNER", roleResult.getName());

        verify(rolePersistencePort, times(1)).getRoleByName(roleName);
    }

    @Test
    void getRoleByNameReturnsRoleNotFoundException(){

        when(rolePersistencePort.getRoleByName(anyString())).thenThrow(new RoleNotFoundException(ErrorMessages.ROLE_NOT_FOUND.getMessage(UserRoles.OWNER.name())));


        assertThrows(RoleNotFoundException.class, () -> roleUseCase.getRoleByName("OWNER"));


        verify(rolePersistencePort, times(1)).getRoleByName("OWNER");
    }
}