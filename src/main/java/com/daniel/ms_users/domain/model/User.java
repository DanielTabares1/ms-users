package com.daniel.ms_users.domain.model;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String cellphone;
    private Date birthDate;
    private String email;
    private String password;
    private Role role;


    public User() {
    }

    public User(Long id, String name, String lastName, String documentNumber, String cellphone, Date birthDate, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.cellphone = cellphone;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
