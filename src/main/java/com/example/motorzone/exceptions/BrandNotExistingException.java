package com.example.motorzone.exceptions;

public class BrandNotExistingException extends RuntimeException{
    public BrandNotExistingException(String message) {
        super(message);
    }
}
