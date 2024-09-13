package com.daniel.ms_users.application.exception;

public class UserUnderageException extends RuntimeException{
    public UserUnderageException(){
        super("The user must be over 18 years old");
    }
}
