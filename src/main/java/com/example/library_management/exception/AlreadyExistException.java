package com.example.library_management.exception;

public class AlreadyExistException extends  RuntimeException{
    public AlreadyExistException(String msg){
         super(msg);
    }
}
