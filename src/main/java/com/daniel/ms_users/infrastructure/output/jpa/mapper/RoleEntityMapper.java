package com.daniel.ms_users.infrastructure.output.jpa.mapper;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.infrastructure.output.jpa.entity.RoleEntity;


public interface RoleEntityMapper {
    Role toRole(RoleEntity roleEntity);
}
