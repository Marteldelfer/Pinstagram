package com.pinstagram.commentservice.controller;

import com.pinstagram.commentservice.dto.CommentRequestDTO;
import com.pinstagram.commentservice.dto.CommentResponseDTO;
import com.pinstagram.commentservice.dto.CommentUpdateRequestDTO;
import com.pinstagram.commentservice.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> findCommentsByPostId(@PathVariable("postId") UUID postId) {
        return ResponseEntity.ok().body(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CommentResponseDTO>> findCommentsByParentId(@PathVariable("parentId") UUID parentId) {
        return ResponseEntity.ok().body(commentService.getAllCommentsByParentId(parentId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable("commentId") UUID commentId) {
        return ResponseEntity.ok().body(commentService.getCommentById(commentId));
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> saveComment(@Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        return ResponseEntity.ok().body(commentService.createComment(commentRequestDTO));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable("commentId") UUID commentId,
            @Valid@RequestBody CommentUpdateRequestDTO updateRequest
    ) {
        return ResponseEntity.ok().body(commentService.updateComment(commentId, updateRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> deleteCommentById(@PathVariable("commentId") UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
