package com.pinstagram.accountservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AccountResponseDto {

    private UUID id;
    private String name;
    private String username;
    private String avatarUrl;
    private String bio;

    private boolean currentUser;
    private boolean followedByCurrentUser;
    private boolean isPrivate;

    private Instant createdAt;

    private long publicationCount;
    private long followerCount;
    private long followingCount;

}
