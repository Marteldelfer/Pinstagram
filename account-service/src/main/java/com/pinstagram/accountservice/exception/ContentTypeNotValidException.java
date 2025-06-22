package com.pinstagram.accountservice.exception;

public class ContentTypeNotValidException extends RuntimeException {
    public ContentTypeNotValidException(String message) {
        super(message);
    }
}
