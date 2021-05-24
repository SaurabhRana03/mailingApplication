package com.projectDemo.mailingApp.exception;


public class InvalidFileException extends  RuntimeException{

    public InvalidFileException(String message) {
        super(message);
    }
}
