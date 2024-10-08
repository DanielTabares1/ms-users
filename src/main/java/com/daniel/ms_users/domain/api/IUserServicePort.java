package com.daniel.ms_users.domain.api;

import com.daniel.ms_users.domain.model.User;

public interface IUserServicePort {
    User saveUser(User user, String roleName);
    User getUserById(Long id);
    boolean existByEmail(String email);
    User getUserByEmail(String email);
}
