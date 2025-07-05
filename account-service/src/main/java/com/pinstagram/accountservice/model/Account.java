package com.pinstagram.accountservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 30, message = "Username must have between 6 and 30 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email formated incorrectly")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 255, message = "Bio must be at most 255 characters long")
    private String bio;

    @NotNull(message = "Deleted field is required") // should be assured inside service logic
    private Boolean deleted;
    @NotNull(message = "isPrivate field is required")
    private Boolean isPrivate;
    @NotNull(message = "Validated field is required")
    private Boolean validated;

    @NotNull(message = "CreateAt field is required") // should be assured inside service logic
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private String avatarUrl;

    @NotNull(message = "publicationCount should is required") // should be assured inside service logic
    private long publicationCount;
    @NotNull(message = "followerCount should is required") // should be assured inside service logic
    private long followerCount;
    @NotNull(message = "followingCount should is required") // should be assured inside service logic
    private long followingCount;

}

