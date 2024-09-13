package com.daniel.ms_users.infrastructure.output.jpa.mapper.impl;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.infrastructure.output.jpa.entity.RoleEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapperImpl implements RoleEntityMapper {
    @Override
    public Role toRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role role = new Role();

        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        role.setDescription(roleEntity.getDescription());

        return role;
    }


}
