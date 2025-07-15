package com.pinstagram.posts_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostRequestDTO {
    
    @NotNull(message = "Image file is required")
    private MultipartFile image;

    @NotNull(message = "Description should not be null")
    @Size(max = 1024, message = "Description should not have more than 1024 characters")
    private String description;

    @NotNull(message = "Post visibility is required")
    private boolean isPubliclyVisible;

    private String locationName;
    private Double latitude;
    private Double longitude;

}
