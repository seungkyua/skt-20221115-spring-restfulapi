package com.example.myrestfulservices.exception;

import com.example.myrestfulservices.user.UserNotFoundException;
import com.example.myrestfulservices.user.UserNotFoundException2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@RestController
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler { // extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//
//        // 질문 테스트 https://www.inflearn.com/questions/499693
//        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
////        return null;
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        ExceptionResponse exceptionResponse=ExceptionResponse.builder()
                .timestamp(new Date())
                .message("Validation Failed")
                .details(ex.getBindingResult().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException2.class)
    public final ResponseEntity<Object> handleUserNotFoundException2(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        System.out.println(request.getParameterNames());
        return new ResponseEntity(exceptionResponse, HttpStatus.INSUFFICIENT_STORAGE);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println(ex.getBindingResult());
//        System.out.println(ex.getMessage());
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
//
//        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
}
