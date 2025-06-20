package com.pinstagram.commentservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ApiError> handleCommentNotFoundException(CommentNotFoundException e) {
        logger.error("Error caught with message: {}", e.getMessage());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.internalServerError().body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorList = e.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errorList)
                .build();
        logger.error("Error caught on fields: {}", apiError.getErrors().toString());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiError> handlePostNotFoundException(PostNotFoundException e) {
        logger.error("Error caught with message: {}", e.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(Instant.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(e.getMessage()))
                .build();
        return ResponseEntity.internalServerError().body(error);
    }
}
