package com.daniel.ms_users.infrastructure.output.jpa.mapper.impl;

import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.output.jpa.entity.RoleEntity;
import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapperImpl implements UserEntityMapper {
    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        RoleEntity role = new RoleEntity(
                user.getRole().getId(),
                user.getRole().getName(),
                user.getRole().getDescription()
        );

        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDocumentNumber(),
                user.getCellphone(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPassword(),
                role
        );
    }

    @Override
    public User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Role role = new Role(
                userEntity.getRole().getId(),
                userEntity.getRole().getName(),
                userEntity.getRole().getDescription()
        );

        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastName(),
                userEntity.getDocumentNumber(),
                userEntity.getCellphone(),
                userEntity.getBirthDate(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                role
        );
    }
}
