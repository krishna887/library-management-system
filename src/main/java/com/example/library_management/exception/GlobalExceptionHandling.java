package com.example.library_management.exception;

import com.example.library_management.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(CustomIllegalStateException.class)
    public ResponseEntity<GenericResponse<String>> handleException(CustomIllegalStateException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("illegal_state_exception","illegal state exception handling")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("resource_not_found_exception","Resource not found exception handling")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("user-name- not-found-ecception","User name not found exception handling")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

}
