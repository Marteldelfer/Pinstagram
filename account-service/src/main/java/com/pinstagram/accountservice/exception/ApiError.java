package com.pinstagram.accountservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiError {

    private Instant timestamp;
    private Integer code;
    private String status;

    List<String> errors;

}
