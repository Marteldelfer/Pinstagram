package com.pinstagram.commentservice.repository;

import com.pinstagram.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("SELECT c FROM Comment c WHERE c.postId = :postId AND c.deleted = false AND c.parentId IS NULL")
    List<Comment> findAllByPostId(UUID postId);
    @Query("SELECT c FROM Comment c WHERE c.parentId = :parentId AND c.deleted = false")
    List<Comment> findAllByParentId(UUID parentId);
    @Query("SELECT c FROM Comment c WHERE c.id = :id AND NOT c.deleted")
    Optional<Comment> findByIdAndNotDeleted(UUID id);
    @Query("SELECT c FROM Comment c WHERE c.deleted = false")
    List<Comment> findAllAndNotDeleted();
}
