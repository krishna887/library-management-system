package com.example.library_management.exception;

public class NotLoggedInUserException extends RuntimeException{
    public NotLoggedInUserException(String msg){
        super(msg);
    }
}
