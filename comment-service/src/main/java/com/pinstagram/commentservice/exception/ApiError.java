package com.pinstagram.commentservice.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
public class ApiError {

    private Instant timestamp;
    private Integer code;
    private String status;

    List<String> errors;

}
