package com.example.library_management.exception;

import com.example.library_management.util.GenericResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<String>> handleException(MethodArgumentNotValidException e, BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("Method Argument not valid","User name not found exception handling")
                .body(GenericResponse.error(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining()), HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GenericResponse<String>> handleException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("Method Argument not valid","User name not found exception handling")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GenericResponse<String>> handleException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("User not matched","User not found")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.value()));
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
//        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
//        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("User not matched","User not found")
                .body(GenericResponse.error(ex.getMessage(), HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.value()));
    }
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(MalformedJwtException ex) {
//        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
//        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("User not matched","User not found")
                .body(GenericResponse.error(ex.getMessage(), HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.value()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponse<String>> handleException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("Access Denied","User not found")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.value()));
    }
    @ExceptionHandler(NotLoggedInUserException.class)
    public ResponseEntity<GenericResponse<String>> handleException(NotLoggedInUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("invalid exception","only the logged in user can change his details")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST,HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<GenericResponse<String>> handleException(AlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("alrady exist exception","already exist")
                .body(GenericResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()));
    }
}

