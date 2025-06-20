package com.pinstagram.commentservice.service;

import com.pinstagram.commentservice.dto.CommentRequestDTO;
import com.pinstagram.commentservice.dto.CommentResponseDTO;
import com.pinstagram.commentservice.dto.CommentUpdateRequestDTO;
import com.pinstagram.commentservice.exception.CommentNotFoundException;
import com.pinstagram.commentservice.exception.PostNotFoundException;
import com.pinstagram.commentservice.grpc.PostValidationGrpcClient;
import com.pinstagram.commentservice.mapper.Mapper;
import com.pinstagram.commentservice.model.Comment;
import com.pinstagram.commentservice.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostValidationGrpcClient postValidationClient;
    private final Logger logger;

    public CommentService(
            CommentRepository commentRepository,
            PostValidationGrpcClient postValidationClient
    ) {
        this.commentRepository = commentRepository;
        this.postValidationClient = postValidationClient;
        this.logger = LoggerFactory.getLogger(CommentService.class);
    }

    private Comment getComment(UUID id) {
        logger.info("getting comment by id {}", id);
        return commentRepository.findByIdAndNotDeleted(id).orElseThrow(
                () -> new CommentNotFoundException("Comment with id " + id + " not found")
        );
    }

    public CommentResponseDTO getCommentById(UUID id) {
        Comment comment = getComment(id);
        return Mapper.toDTO(comment);
    }

    public CommentResponseDTO createComment(CommentRequestDTO commentRequest) {
        logger.info("creating comment from request with content {}", commentRequest.getContent());

        if (commentRequest.getParentId() != null) {
            commentRepository.findById(commentRequest.getParentId()).orElseThrow(
                    () -> new CommentNotFoundException("Parent Comment with id " + commentRequest.getParentId() + " not found")
            );
        }
        if (!postValidationClient.checkPostExists(commentRequest.getPostId())) {
            throw new PostNotFoundException("Post with id " + commentRequest.getPostId() + " not found");
        }
        Comment comment = Mapper.toComment(commentRequest);

        comment.setCreatedAt(Instant.now());
        comment.setEdited(false);
        comment.setDeleted(false);
        comment.setCommentCount(0);
        comment.setLikeCount(0);
        return Mapper.toDTO(commentRepository.save(comment));
    }

    public CommentResponseDTO updateComment(UUID id, CommentUpdateRequestDTO commentRequest) {
        Comment comment = getComment(id);
        comment.setContent(commentRequest.getContent());
        comment.setEdited(true);
        comment.setEditedAt(Instant.now());
        logger.info("updating comment with id {}", id);

        return Mapper.toDTO(commentRepository.save(comment));
    }

    public void deleteComment(UUID id) {
        Comment comment = getComment(id);
        logger.info("deleting comment with id {}", id);

        comment.setDeleted(true);
        comment.setDeletedAt(Instant.now());
        commentRepository.save(comment);
    }

    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAllAndNotDeleted().stream().map(Mapper::toDTO).toList();
    }

    public List<CommentResponseDTO> getAllCommentsByPostId(UUID postId) {
        return commentRepository.findAllByPostId(postId).stream().map(Mapper::toDTO).toList();
    }

    public List<CommentResponseDTO> getAllCommentsByParentId(UUID parentId) {
        return commentRepository.findAllByParentId(parentId).stream().map(Mapper::toDTO).toList();
    }
}
