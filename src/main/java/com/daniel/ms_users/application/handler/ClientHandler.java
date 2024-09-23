package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.exception.EmailAlreadyInUseException;
import com.daniel.ms_users.application.mapper.IClientRequestMapper;
import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.Role;
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
    private final IRoleServicePort roleServicePort;
    private final IClientRequestMapper clientRequestMapper;
    private final PasswordEncoderUtil passwordEncoderUtil;


    @Override
    public User saveClient(ClientRequest clientRequest) {
        if (userServicePort.existByEmail(clientRequest.getEmail())) {
            throw new EmailAlreadyInUseException("Email " + clientRequest.getEmail() + " is already assigned to an account");
        }
        User user = clientRequestMapper.toModel(clientRequest);
        Role role = roleServicePort.getRoleByName(UserRoles.CLIENT.toString());
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userServicePort.saveUser(user);
    }
}
