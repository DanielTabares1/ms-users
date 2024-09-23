package com.daniel.ms_users.application.mapper.impl;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.application.mapper.IOwnerRequestMapper;
import com.daniel.ms_users.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class OwnerRequestMapper implements IOwnerRequestMapper {
    @Override
    public User toUser(OwnerRequest ownerRequest) {
        if ( ownerRequest == null ) {
            return null;
        }

        return new User(null,
                ownerRequest.getName(),
                ownerRequest.getLastName(),
                ownerRequest.getDocumentNumber(),
                ownerRequest.getCellphone(),
                ownerRequest.getBirthDate(),
                ownerRequest.getEmail(),
                ownerRequest.getPassword(),
                null
        );
    }
}
