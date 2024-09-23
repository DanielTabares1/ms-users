package com.daniel.ms_users.application.handler.impl;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.exception.EmailAlreadyInUseException;
import com.daniel.ms_users.application.handler.IEmployeeHandler;
import com.daniel.ms_users.application.mapper.IEmployeeRequestMapper;
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
@RequiredArgsConstructor
@Transactional
public class EmployeeHandler implements IEmployeeHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IEmployeeRequestMapper employeeRequestMapper;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public User saveEmployee(EmployeeRequest employeeRequest) {
        if (userServicePort.existByEmail(employeeRequest.getEmail())){
            throw new EmailAlreadyInUseException("Email :" + employeeRequest.getEmail() + " is already assigned to an account");
        }
        User user = employeeRequestMapper.toModel(employeeRequest);
        Role role = roleServicePort.getRoleByName(UserRoles.EMPLOYEE.toString());
        user.setRole(role);
        user.setPassword(passwordEncoderUtil.encode(user.getPassword()));
        return userServicePort.saveUser(user);
    }
}
