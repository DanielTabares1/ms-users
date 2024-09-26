package com.daniel.ms_users.domain.exception;

public class UserUnderageException extends RuntimeException{
    public UserUnderageException(String message){
        super(message);
    }
}
