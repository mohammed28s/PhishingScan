package com.PhishingScan.email.ExceptionHandling;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}