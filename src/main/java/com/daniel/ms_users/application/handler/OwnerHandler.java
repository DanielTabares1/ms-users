package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.exception.EmailAlreadyInUseException;
import com.daniel.ms_users.application.exception.UserUnderageException;
import com.daniel.ms_users.application.mapper.OwnerRequestMapper;
import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import com.daniel.ms_users.application.util.UserValidations;
import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.UserRoles;
import com.daniel.ms_users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler{

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final OwnerRequestMapper ownerRequestMapper;
    private final UserValidations userValidations;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public User saveOwner(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toUser(ownerRequest);
        if(!userValidations.isAdult(user)){
            throw new UserUnderageException();
        }
        String requestEmail = ownerRequest.getEmail();
        if(userServicePort.existByEmail(requestEmail)){
            throw new EmailAlreadyInUseException("User with email: " + requestEmail + " is already in use by another user");
        }
        Role role = roleServicePort.getRoleByName(UserRoles.OWNER.toString());
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userServicePort.saveUser(user);
    }


}
