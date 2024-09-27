package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.domain.model.User;

public interface IOwnerHandler {
    User saveOwner(OwnerRequest ownerRequest);
}
