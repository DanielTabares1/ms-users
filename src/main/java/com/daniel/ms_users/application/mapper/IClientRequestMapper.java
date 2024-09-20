package com.daniel.ms_users.application.mapper;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.domain.model.User;

public interface IClientRequestMapper {
    User toModel(ClientRequest clientRequest);

}
