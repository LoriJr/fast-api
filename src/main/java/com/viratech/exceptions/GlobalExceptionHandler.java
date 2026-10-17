package com.viratech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        int status = HttpStatus.NOT_FOUND.value();
        String message = ex.getMessage();
        ErrorResponse error = new ErrorResponse(
                status,
                message,
                null
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidationFieldsException(MethodArgumentNotValidException ex){
        int status = HttpStatus.BAD_REQUEST.value();
        String message = "Dados inválidos";

        List<ErrorResponse.ObjectError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(v -> new ErrorResponse.ObjectError(v.getField(), v.getDefaultMessage())
                ).toList();
        return ResponseEntity.status(status).body(
                new ErrorResponse(
                        status, message, errors
                ));
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidCategoryException(InvalidCategoryException ex){
        int status = HttpStatus.BAD_REQUEST.value();
        String message = ex.getMessage();
        ErrorResponse error = new ErrorResponse(
                status,
                message,
                null
        );
        return ResponseEntity.status(status).body(error);
    }



}
