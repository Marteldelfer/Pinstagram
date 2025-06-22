package com.pinstagram.accountservice.exception;

public class InvalidImageDimensionException extends RuntimeException {
    public InvalidImageDimensionException(String message) {
        super(message);
    }
}
