package com.daniel.ms_users.domain.spi;

import com.daniel.ms_users.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    boolean existByEmail(String email);
    Optional<User> getUserByEmail(String email);
}
