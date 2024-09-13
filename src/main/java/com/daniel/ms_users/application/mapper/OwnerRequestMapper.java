package com.daniel.ms_users.application.mapper;

import com.daniel.ms_users.application.dto.OwnerRequest;
import com.daniel.ms_users.domain.model.User;

public interface OwnerRequestMapper {
    User toUser(OwnerRequest ownerRequest);
}
