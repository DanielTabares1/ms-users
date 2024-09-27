package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.domain.exception.RoleNotFoundException;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByName(String name) {
        return rolePersistencePort.getRoleByName(name).orElseThrow(
                () -> new RoleNotFoundException(ErrorMessages.ROLE_NOT_FOUND.getMessage(name))
        );
    }
}
