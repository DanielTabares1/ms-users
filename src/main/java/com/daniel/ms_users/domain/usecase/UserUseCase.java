package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;

    public class UserUseCase implements IUserServicePort {

        private final IUserPersistencePort userPersistencePort;

        public UserUseCase(IUserPersistencePort userPersistencePort) {
            this.userPersistencePort = userPersistencePort;
        }

        @Override
        public User saveUser(User user) {
            return userPersistencePort.saveUser(user);
        }
    }
