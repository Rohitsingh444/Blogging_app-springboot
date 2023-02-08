package com.springBoot.blogApp.exceptions;

import com.springBoot.blogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // here we will handle all custom exceptions

    @ExceptionHandler(ResourceNotFoundException.class)  //this is user defined exception
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false,HttpStatus.NOT_FOUND.value(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
    // handle custom validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)  //this exception we not created it will run when we will get this default exception in postman
//    public ResponseEntity<?> handleMethodValidException(MethodArgumentNotValidException exception) {  // use for validation
//        ApiResponse apiResponse = new ApiResponse("Invalid Data Added or Validation Error",false,HttpStatus.BAD_REQUEST.value(),new Date() ,exception.getBindingResult().getFieldError().getDefaultMessage());
//        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
//    }
        //OR
    public ResponseEntity<Map<String, String>> handleMethodValidException(MethodArgumentNotValidException exception) {
        //Invalid field will show only in output
        Map<String,String> response = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((error)->{  //FiledErrors is list of all errors and using foreach we r traversing 1 by 1
            // System.out.println(error);
            String fieldName=error.getField();  //error field name
            String message = error.getDefaultMessage(); //error field message
            response.put(fieldName,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

}

// exception.getBindingResult().getFieldErrors().forEach((error) fetech from this output
/* "errors": [
        {
            "codes": [
                "Email.userDto.email",
                "Email.email",
                "Email.java.lang.String",
                "Email"
            ],
            "arguments": [
                {
                    "codes": [
                        "userDto.email",
                        "email"
                    ],
                    "arguments": null,
                    "defaultMessage": "email",
                    "code": "email"
                },
                [],
                {
                    "defaultMessage": ".*",
                    "arguments": null,
                    "codes": [
                        ".*"
                    ]
                }
            ],
            "defaultMessage": "Invalid email address",
            "objectName": "userDto",
            "field": "email",
            "rejectedValue": "ssgr",
            "bindingFailure": false,
            "code": "Email"
        },
        {
            "codes": [
                "Size.userDto.name",
                "Size.name",
                "Size.java.lang.String",
                "Size"
            ],
            "arguments": [
                {
                    "codes": [
                        "userDto.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                },
                2147483647,
                4
            ],
            "defaultMessage": "name should have least 4 characters",
            "objectName": "userDto",
            "field": "name",
            "rejectedValue": "12",
            "bindingFailure": false,
            "code": "Size"
        }
    ]
 */