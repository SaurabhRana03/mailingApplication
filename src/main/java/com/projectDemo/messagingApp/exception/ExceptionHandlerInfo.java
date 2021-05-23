package com.projectDemo.messagingApp.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerInfo {

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> handleException(InvalidFileException exception) {

       ExceptionInfo exInfo = new ExceptionInfo(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, ZonedDateTime.now());

        return new ResponseEntity<>(exInfo, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);

    }
}