package com.pinstagram.commentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;
    private UUID parentId;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private Instant createdAt;
    private Instant editedAt;
    private Instant deletedAt;

    @NotNull
    private boolean edited;
    @NotNull
    private boolean deleted;

    @NotNull
    private long likeCount;
    @NotNull
    private long commentCount;

}
