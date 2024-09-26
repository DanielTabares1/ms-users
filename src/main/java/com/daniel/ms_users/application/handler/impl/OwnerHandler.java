package com.daniel.ms_users.application.handler.impl;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.handler.IOwnerHandler;
import com.daniel.ms_users.application.mapper.IOwnerRequestMapper;
import com.daniel.ms_users.domain.model.UserRoles;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {

    private final IUserServicePort userServicePort;
    private final IOwnerRequestMapper ownerRequestMapper;

    @Override
    public User saveOwner(OwnerRequest ownerRequest) {
        User user = ownerRequestMapper.toUser(ownerRequest);
        return userServicePort.saveUser(user, UserRoles.OWNER.name());
    }

}
