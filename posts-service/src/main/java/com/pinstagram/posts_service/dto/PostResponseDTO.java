package com.pinstagram.posts_service.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {

    private UUID id;
    private UUID authorId;

    private String imageUrl;
    private String description;

    private Instant postedAt;

    private long viewCount;
    private long commentCount;
    private long likeCount;

    private boolean likedByCurrentUser;

    private boolean isEdited;

    private String locationName;
    private Double latitude;
    private Double longitude;

}
