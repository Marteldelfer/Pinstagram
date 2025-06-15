package com.pinstagram.posts_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                (error) -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ContentTypeNotValidException.class)
    public ResponseEntity<Map<String, String>> handleContentTypeNotValidException(ContentTypeNotValidException e) {
        logger.warn(e.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ImageUploadFailedException.class)
    public ResponseEntity<Map<String, String>> handleImageUploadFailedException(ImageUploadFailedException e) {
        logger.warn(e.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Error while uploading image.");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePostNotFoundException(PostNotFoundException e) {
        logger.warn("Post not found: {}", e.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Post not found.");
        return ResponseEntity.badRequest().body(errors);
    }
}
