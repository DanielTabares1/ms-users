package com.daniel.ms_users.domain.spi;

import com.daniel.ms_users.domain.model.Role;

public interface IRolePersistencePort {
    Role getRoleByName(String name);
}
