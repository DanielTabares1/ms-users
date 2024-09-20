package com.daniel.ms_users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    private String name;
    private String lastName;
    private String documentNumber;
    private String cellphone;
    private String email;
    private String password;


}
