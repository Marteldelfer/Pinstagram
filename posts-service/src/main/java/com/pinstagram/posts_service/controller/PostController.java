package com.pinstagram.posts_service.controller;

import com.pinstagram.posts_service.dto.PostRequestDTO;
import com.pinstagram.posts_service.dto.PostResponseDTO;
import com.pinstagram.posts_service.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@Tag(name = "Post", description = "API for managing posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() { // TODO remove method; Made for testing
        return ResponseEntity.ok().body(postService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable UUID id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @PostMapping
    @Operation(summary = "Create new post")
    public ResponseEntity<PostResponseDTO> savePost(
            @ModelAttribute @Valid PostRequestDTO requestDTO,
            @RequestHeader("x-user-id") String userId
    ) {
        return ResponseEntity.ok().body(postService.createPost(requestDTO, userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePostById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update post")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable UUID id,
            @ModelAttribute @Valid PostRequestDTO requestDTO,
            @RequestHeader("x-user-id") String userId
    ) {
        return ResponseEntity.ok().body(postService.updatePost(id, requestDTO, userId));
    }
}
