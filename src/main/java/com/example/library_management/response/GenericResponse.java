package com.example.library_management.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
public class GenericResponse<T> {
    private String message;
    private HttpStatus httpStatus;
    private int StatusCode;
    private boolean status;
    private LocalTime updatedTime;
    private T data;


    public static <T> GenericResponse<T> empty(String message,HttpStatus httpStatus, int statusCode) {
        return GenericResponse.<T>builder()
                .message(message)
                .httpStatus(httpStatus)
                .StatusCode(statusCode)
                .updatedTime(LocalTime.now())
                .status(true)
                .build();
    }

    public static <T> GenericResponse<T> success(T data, String message ,HttpStatus httpStatus, int statusCode) {
        return GenericResponse.<T>builder()
                .message(message)
                .httpStatus(httpStatus)
                .StatusCode(statusCode)
                .status(true)
                .updatedTime(LocalTime.now())
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> error(String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .status(false)
                .data(null)
                .build();
    }
}