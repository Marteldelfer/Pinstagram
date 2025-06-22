package com.pinstagram.accountservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "UserName is required")
    @Size(min = 6, max = 30, message = "Username must have between 6 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]{6,30}$", message = "Username can only contain letters, numbers, dots and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email formated incorrectly")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 60, message = "Password should have between 6 and 60 characters")
    private String password;
}
