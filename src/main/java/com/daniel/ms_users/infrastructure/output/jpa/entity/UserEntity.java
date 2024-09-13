package com.daniel.ms_users.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Valid()
    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String cellphone;
    private Date birthDate;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

}
