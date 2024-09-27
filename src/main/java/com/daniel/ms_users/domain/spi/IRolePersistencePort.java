package com.daniel.ms_users.domain.spi;

import com.daniel.ms_users.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getRoleByName(String name);
}
