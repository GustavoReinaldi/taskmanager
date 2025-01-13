package com.stefanini.taskmanager.exceptions;


public class BadRequestException extends RuntimeException{
    private BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
