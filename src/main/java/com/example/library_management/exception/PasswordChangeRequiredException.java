package com.example.library_management.exception;

public class PasswordChangeRequiredException extends RuntimeException {
    public PasswordChangeRequiredException(String msg) {
        super(msg);
    }
}
