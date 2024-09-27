package com.daniel.ms_users.application.handler;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.domain.model.User;

public interface IEmployeeHandler {
    User saveEmployee(EmployeeRequest employeeRequest);
}
