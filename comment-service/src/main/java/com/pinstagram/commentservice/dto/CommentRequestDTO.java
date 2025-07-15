package com.pinstagram.commentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentRequestDTO {

    @NotNull
    private UUID postId;

    private UUID parentId;

    @NotBlank
    private String content;
}
