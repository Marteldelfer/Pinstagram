package com.pinstagram.accountservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AccountEditPicDto {

    @NotNull(message = "Image is required")
    private MultipartFile image;

}
