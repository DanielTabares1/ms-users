package com.daniel.ms_users.application.mapper.impl;

import com.daniel.ms_users.application.dto.EmployeeRequest;
import com.daniel.ms_users.application.mapper.IEmployeeRequestMapper;
import com.daniel.ms_users.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequestMapper implements IEmployeeRequestMapper {
    @Override
    public User toModel(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        return new User(null,
                employeeRequest.getName(),
                employeeRequest.getLastName(),
                employeeRequest.getDocumentNumber(),
                employeeRequest.getCellphone(),
                null,
                employeeRequest.getEmail(),
                employeeRequest.getPassword(),
                null
        );
    }
}
