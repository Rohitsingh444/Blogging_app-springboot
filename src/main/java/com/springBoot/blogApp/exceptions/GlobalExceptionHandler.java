package com.springBoot.blogApp.exceptions;

import com.springBoot.blogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        String message = exception.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, false,HttpStatus.NOT_FOUND.value(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
}
