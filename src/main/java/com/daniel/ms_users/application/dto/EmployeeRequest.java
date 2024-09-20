package com.daniel.ms_users.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String name;
    private String lastName;
    private String documentNumber;
    private String cellphone;
    private String email;
    private String password;

}
