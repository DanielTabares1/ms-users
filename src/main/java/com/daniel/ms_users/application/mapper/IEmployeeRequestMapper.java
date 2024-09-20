package com.daniel.ms_users.application.mapper;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.domain.model.User;

public interface IEmployeeRequestMapper {
    User toModel(EmployeeRequest employeeRequest);
}
