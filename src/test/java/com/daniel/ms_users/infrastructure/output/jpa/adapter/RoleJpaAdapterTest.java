package com.daniel.ms_users.infrastructure.output.jpa.adapter;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.infrastructure.exception.RoleNotFoundException;
import com.daniel.ms_users.infrastructure.output.jpa.entity.RoleEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class RoleJpaAdapterTest {

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private RoleEntityMapper roleEntityMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

            @Test
            void getRoleByNameReturnsSuccess() {
                RoleEntity roleEntity = new RoleEntity(4, "OWNER", "El dueño de un chuzo");
                Role expectedRole = new Role(4, "OWNER", "El dueño de un chuzo");

                when(roleRepository.findByName("OWNER")).thenReturn(Optional.of(roleEntity));
                when(roleEntityMapper.toRole(roleEntity)).thenReturn(expectedRole);

                Role role = roleJpaAdapter.getRoleByName("OWNER");

                assertNotNull(role);
                assertEquals("OWNER", role.getName());

                verify(roleRepository, times(1)).findByName("OWNER");
                verify(roleEntityMapper, times(1)).toRole(roleEntity);

            }

            @Test
            void getRoleByNameReturnsRoleNotFound(){
                when(roleRepository.findByName("OWNER")).thenThrow(new RoleNotFoundException());

                assertThrows(RoleNotFoundException.class, () -> roleJpaAdapter.getRoleByName("OWNER"));

                verify(roleRepository, times(1)).findByName("OWNER");
            }
}