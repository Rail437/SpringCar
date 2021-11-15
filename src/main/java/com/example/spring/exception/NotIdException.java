package com.example.spring.exception;


public class NotIdException extends RuntimeException {
    public NotIdException(String message) {
        super(message);
    }
}
