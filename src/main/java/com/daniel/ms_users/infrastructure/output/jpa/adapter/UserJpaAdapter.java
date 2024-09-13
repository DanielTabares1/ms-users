package com.daniel.ms_users.infrastructure.output.jpa.adapter;

import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userRepository.save(userEntity);
    }
}
