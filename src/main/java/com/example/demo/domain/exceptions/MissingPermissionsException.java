package com.example.demo.domain.exceptions;

public class MissingPermissionsException extends RuntimeException {
    public MissingPermissionsException(String message) {
        super(message);
    }
}
