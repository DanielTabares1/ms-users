package com.daniel.ms_users.infrastructure.output.jpa.mapper;

import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;

public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);
}
