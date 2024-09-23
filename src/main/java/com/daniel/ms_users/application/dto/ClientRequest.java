package com.daniel.ms_users.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotBlank(message = "Client name cannot be empty")
    private String name;

    @NotBlank(message = "Client last name cannot be empty")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "The document number must be numeric")
    private String documentNumber;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{10,12}$", message = "Cellphone must be at least 10 digits and a maximum of 13 including the prefix '+'")
    private String cellphone;

    @Email(message = "Must be a well formed email")
    private String email;

    @NotBlank
    private String password;


}
