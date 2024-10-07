package com.example.motorzone.exceptions;

public class ModelNotExistingException extends RuntimeException {
    public ModelNotExistingException(String message) {
        super(message);
    }
}