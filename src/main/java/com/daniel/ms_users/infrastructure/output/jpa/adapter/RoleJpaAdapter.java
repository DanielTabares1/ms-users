package com.daniel.ms_users.infrastructure.output.jpa.adapter;

import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.exception.RoleNotFoundException;
import com.daniel.ms_users.infrastructure.output.jpa.entity.RoleEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role getRoleByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name).orElseThrow(
                () -> new RoleNotFoundException(ErrorMessages.ROLE_NOT_FOUND.getMessage(name))
        );
        return roleEntityMapper.toRole(roleEntity);
    }
}
