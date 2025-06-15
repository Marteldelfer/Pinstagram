package com.pinstagram.posts_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private UUID authorId;

    @NotNull
    private String imageUrl;
    @NotNull
    private String description;

    @NotNull
    private Instant postedAt;
    @NotNull
    private Instant updatedAt;

    @NotNull
    private long viewCount;
    @NotNull
    private long commentCount;
    @NotNull
    private long likeCount;

    @NotNull
    private boolean isPublicVisible;
    @NotNull
    private boolean isEdited;
    @NotNull
    private boolean isDeleted;
    private Instant deletedAt;

    private String locationName;
    private Double latitude;
    private Double longitude;
}
