package com.daniel.ms_users.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String documentNumber;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{10,12}$", message = "Cellphone must be 10 digits")
    private String cellphone;

//    @Pattern(regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date of birth must be in the format yyyy-MM-dd and valid")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;

    @Email(message = "Must be a well formed email")
    private String email;

    @NotBlank
    private String password;
}
