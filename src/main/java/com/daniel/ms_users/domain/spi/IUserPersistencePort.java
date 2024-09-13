package com.daniel.ms_users.domain.spi;

import com.daniel.ms_users.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
}
