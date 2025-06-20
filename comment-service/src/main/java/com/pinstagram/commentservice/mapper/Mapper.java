package com.pinstagram.commentservice.mapper;

import com.pinstagram.commentservice.dto.CommentRequestDTO;
import com.pinstagram.commentservice.dto.CommentResponseDTO;
import com.pinstagram.commentservice.model.Comment;

public class Mapper {

    public static CommentResponseDTO toDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .commentId(comment.getId())
                .postId(comment.getPostId())
                .authorId(comment.getAuthorId())
                .parentId(comment.getParentId())
                .createdAt(comment.getCreatedAt())
                .editedAt(comment.getEditedAt())
                .edited(comment.isEdited())
                .content(comment.getContent())
                .build();
    }

    public static Comment toComment(CommentRequestDTO commentRequestDTO) {
        return Comment.builder()
                .postId(commentRequestDTO.getPostId())
                .authorId(commentRequestDTO.getAuthorId())
                .parentId(commentRequestDTO.getParentId())
                .content(commentRequestDTO.getContent())
                .build();
    }
}
