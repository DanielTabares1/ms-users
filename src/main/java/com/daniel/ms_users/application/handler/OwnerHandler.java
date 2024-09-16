package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.exception.UserUnderageException;
import com.daniel.ms_users.application.mapper.OwnerRequestMapper;
import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import com.daniel.ms_users.application.util.UserValidationImpl;
import com.daniel.ms_users.application.util.UserValidations;
import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
import com.daniel.ms_users.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Role role = roleServicePort.getRoleByName("OWNER");
        User user = ownerRequestMapper.toUser(ownerRequest);
        if(!userValidations.isAdult(user)){
            throw new UserUnderageException();
        }
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userServicePort.saveUser(user);
    }


}
