package com.daniel.ms_users.domain.exception;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException(String message){
        super(message);
    }
}
