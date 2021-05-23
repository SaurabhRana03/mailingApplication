package com.projectDemo.messagingApp.exceptionHandler;

import org.springframework.stereotype.Component;

public class InvalidFileException extends  RuntimeException{

    public InvalidFileException(String message) {
        super(message);
    }
}
