package com.daniel.ms_users.application.mapper.impl;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.application.mapper.IClientRequestMapper;
import com.daniel.ms_users.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper implements IClientRequestMapper {
    @Override
    public User toModel(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        return new User(null,
                clientRequest.getName(),
                clientRequest.getLastName(),
                clientRequest.getDocumentNumber(),
                clientRequest.getCellphone(),
                null,
                clientRequest.getEmail(),
                clientRequest.getPassword(),
                null
        );
    }
}
