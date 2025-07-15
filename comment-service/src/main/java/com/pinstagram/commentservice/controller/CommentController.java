package com.pinstagram.commentservice.controller;

import com.pinstagram.commentservice.dto.CommentRequestDTO;
import com.pinstagram.commentservice.dto.CommentResponseDTO;
import com.pinstagram.commentservice.dto.CommentUpdateRequestDTO;
import com.pinstagram.commentservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comment", description = "API for managing comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @Operation(description = "Find all comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @GetMapping("/post/{postId}")
    @Operation(description = "Find all comments with postId")
    public ResponseEntity<List<CommentResponseDTO>> findCommentsByPostId(@PathVariable("postId") UUID postId) {
        return ResponseEntity.ok().body(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/parent/{parentId}")
    @Operation(description = "Find all children of comment with parentId")
    public ResponseEntity<List<CommentResponseDTO>> findCommentsByParentId(@PathVariable("parentId") UUID parentId) {
        return ResponseEntity.ok().body(commentService.getAllCommentsByParentId(parentId));
    }

    @GetMapping("/{commentId}")
    @Operation(description = "Find Comment by commentId")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable("commentId") UUID commentId) {
        return ResponseEntity.ok().body(commentService.getCommentById(commentId));
    }

    @PostMapping
    @Operation(description = "Create new comment")
    public ResponseEntity<CommentResponseDTO> saveComment(
            @Valid @RequestBody CommentRequestDTO commentRequestDTO,
            @RequestHeader("x-user-id") String userId
    ) {
        return ResponseEntity.ok().body(commentService.createComment(commentRequestDTO, userId));
    }

    @PutMapping("/{commentId}")
    @Operation(description = "Update comment content")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable("commentId") UUID commentId,
            @Valid@RequestBody CommentUpdateRequestDTO updateRequest,
            @RequestHeader("x-user-id") String userId
    ) {
        return ResponseEntity.ok().body(commentService.updateComment(commentId, updateRequest, userId));
    }

    @DeleteMapping("/{commentId}")
    @Operation(description = "Delete comment")
    public ResponseEntity<CommentResponseDTO> deleteCommentById(@PathVariable("commentId") UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
