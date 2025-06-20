package com.pinstagram.commentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDTO {
    @NotBlank(message = "Update request content should not be blank")
    private String content;
}
