package com.pinstagram.posts_service.exception;

public class ContentTypeNotValidException extends RuntimeException {
    public ContentTypeNotValidException(String message) {
        super(message);
    }
}
