//package com.example.library_management.exception;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.nio.file.AccessDeniedException;
//
//@RestControllerAdvice
//public class CustomExceptionHandling {
//    @ExceptionHandler(Exception.class)
//      public ProblemDetail handleSecurityException(Exception ex){
//            ProblemDetail errorDetails=null;
//        if (ex instanceof AccessDeniedException){
//             errorDetails= ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),ex.getMessage());
//            errorDetails.setProperty("name","Authorization Error");
//        }
//        if (ex instanceof AccessDeniedException){
//            errorDetails= ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),ex.getMessage());
//            errorDetails.setProperty("name","Authorization Error");
//        }
//        if (ex instanceof ExpiredJwtException){
//            errorDetails= ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
//            errorDetails.setProperty("Jwt expired"," jwt is already expired");
//        }
//        if (ex instanceof MalformedJwtException){
//            errorDetails= ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
//            errorDetails.setProperty("Jwt format Exception","jwt format incorrect");
//        }
//
//
//        return errorDetails;
//    }
//}
