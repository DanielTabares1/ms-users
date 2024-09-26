package com.daniel.ms_users.application.handler.impl;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.handler.IClientHandler;
import com.daniel.ms_users.application.mapper.IClientRequestMapper;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.model.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientHandler implements IClientHandler {

    private final IUserServicePort userServicePort;
    private final IClientRequestMapper clientRequestMapper;


    @Override
    public User saveClient(ClientRequest clientRequest) {
        User user = clientRequestMapper.toModel(clientRequest);
        return userServicePort.saveUser(user, UserRoles.CLIENT.name());
    }
}
