package com.example.form.exceptionhandlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.form.allExceptions.DataNotFoundExceptions;
import com.example.form.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(DataNotFoundExceptions.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundExceptions exception) {

        ErrorResponse dataNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Data not found");
        return new ResponseEntity<>(dataNotFound, HttpStatus.NOT_FOUND);

    }

}
