package com.daniel.ms_users.infrastructure.output.jpa.adapter;

import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.domain.exception.UserNotFoundException;
import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        return userEntityMapper.toUser(userRepository.save(userEntity));
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage(id))
        );
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_EMAIL.getMessage(email)));
        return userEntityMapper.toUser(userEntity);
    }
}
