package com.pinstagram.commentservice.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {

    private UUID commentId;
    private UUID postId;
    private UUID parentId;

    private String content;

    private Instant createdAt;
    private Instant editedAt;

    private boolean edited;
    private boolean likedByCurrentUser;

}
