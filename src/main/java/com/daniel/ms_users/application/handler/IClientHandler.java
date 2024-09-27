package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.ClientRequest;
import com.daniel.ms_users.domain.model.User;

public interface IClientHandler {
    User saveClient(ClientRequest clientRequest);

}
