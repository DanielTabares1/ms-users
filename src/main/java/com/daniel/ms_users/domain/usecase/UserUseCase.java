package com.daniel.ms_users.domain.usecase;

import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.exception.*;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.model.UserRoles;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.domain.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.util.UserValidations;

import java.util.Objects;

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
        if(Objects.equals(roleName, UserRoles.OWNER.toString()) && !userValidations.isAdult(user)) {
                throw new UserUnderageException(ErrorMessages.USER_UNDERAGE.getMessage());
        }
        String requestEmail = user.getEmail();
        if (userPersistencePort.existByEmail(requestEmail)) {
            throw new EmailAlreadyInUseException(ErrorMessages.EMAIL_ALREADY_IN_USE.getMessage(requestEmail));
        }
        Role role = rolePersistencePort.getRoleByName(roleName).orElseThrow(
                () -> new RoleNotFoundException(ErrorMessages.ROLE_NOT_FOUND.getMessage(roleName))
        );
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userPersistencePort.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id).orElseThrow(
                () -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage(id))
        );
    }

    @Override
    public boolean existByEmail(String email) {
        return userPersistencePort.existByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_EMAIL.getMessage(email))
        );
    }
}
