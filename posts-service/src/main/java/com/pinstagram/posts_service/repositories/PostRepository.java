package com.pinstagram.posts_service.repositories;

import com.pinstagram.posts_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false ")
    List<Post> findAllNotIsDeleted();

    @Query("SELECT p FROM Post p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Post> findByIdAndNotDeleted(@Param("id") UUID id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Post p " +
            "WHERE p.id = :id AND p.isDeleted = false")
    boolean existsByIdAndDeletedFalse(@Param("id") UUID id);
}
