package com.daniel.ms_users.domain.api;

import com.daniel.ms_users.domain.model.User;

public interface IUserServicePort {
    User saveUser(User user);
    User getUserById(Long id);
}
