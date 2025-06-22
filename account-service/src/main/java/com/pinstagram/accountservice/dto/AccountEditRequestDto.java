package com.pinstagram.accountservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEditRequestDto {

    @NotNull(message = "Bio must not be null")
    private String bio;
    @NotNull(message = "isPrivate must not be null")
    private Boolean isPrivate;

}
