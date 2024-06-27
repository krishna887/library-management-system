package com.example.library_management.exception;

public class CustomIllegalStateException extends RuntimeException{
    public CustomIllegalStateException(String msg){
        super(msg);
    }
}