package com.daniel.ms_users.infrastructure.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(){
        super("Role not found.");
    }
}
