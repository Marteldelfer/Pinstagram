package com.pinstagram.accountservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ContentTypeNotValidException.class)
    public ResponseEntity<ApiError> handleContentTypeNotValidException(ContentTypeNotValidException e) {
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(InvalidImageDimensionException.class)
    public ResponseEntity<ApiError> handleInvalidImageDimensionException(InvalidImageDimensionException e) {
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(UserAccountNotFoundException.class)
    public ResponseEntity<ApiError> handleUserAccountNotFoundException(UserAccountNotFoundException e) {
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ImageUploadFailedException.class)
    public ResponseEntity<ApiError> handleImageUploadFailedException(ImageUploadFailedException e) {
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getFieldErrors().stream()
                .map((error) -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), errors);
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        logger.error("{} caught with error: {}", e.getClass().getSimpleName(), e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
