package com.daniel.ms_users.application.handler.impl;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.handler.IEmployeeHandler;
import com.daniel.ms_users.application.mapper.IEmployeeRequestMapper;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.model.User;
import com.daniel.ms_users.domain.model.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeHandler implements IEmployeeHandler {

    private final IUserServicePort userServicePort;
    private final IEmployeeRequestMapper employeeRequestMapper;

    @Override
    public User saveEmployee(EmployeeRequest employeeRequest) {
        User user = employeeRequestMapper.toModel(employeeRequest);
        return userServicePort.saveUser(user,UserRoles.EMPLOYEE.toString());
    }
}
