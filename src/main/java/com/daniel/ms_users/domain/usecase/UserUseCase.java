package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.exception.EmailAlreadyInUseException;
import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.domain.exception.UserUnderageException;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.domain.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.util.UserValidations;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserValidations userValidations;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final IRolePersistencePort rolePersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, UserValidations userValidations, PasswordEncoderUtil passwordEncoderUtil, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.userValidations = userValidations;
        this.passwordEncoderUtil = passwordEncoderUtil;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public User saveUser(User user, String roleName) {
        if (!userValidations.isAdult(user)) {
            throw new UserUnderageException(ErrorMessages.USER_UNDERAGE.getMessage());
        }
        String requestEmail = user.getEmail();
        if (userPersistencePort.existByEmail(requestEmail)) {
            throw new EmailAlreadyInUseException(ErrorMessages.EMAIL_ALREADY_IN_USE.getMessage(requestEmail));
        }
        Role role = rolePersistencePort.getRoleByName(roleName);
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userPersistencePort.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userPersistencePort.existByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}
