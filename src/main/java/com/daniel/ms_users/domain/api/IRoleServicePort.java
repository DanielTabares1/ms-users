package com.daniel.ms_users.domain.api;

import com.daniel.ms_users.domain.model.Role;


public interface IRoleServicePort {
    Role getRoleByName(String name);
}
