package com.pinstagram.posts_service.service;

import com.pinstagram.posts_service.dto.PostRequestDTO;
import com.pinstagram.posts_service.dto.PostResponseDTO;
import com.pinstagram.posts_service.exception.ContentTypeNotValidException;
import com.pinstagram.posts_service.exception.ImageUploadFailedException;
import com.pinstagram.posts_service.exception.PostNotFoundException;
import com.pinstagram.posts_service.mapper.Mapper;
import com.pinstagram.posts_service.model.Post;
import com.pinstagram.posts_service.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final StorageService storageService;
    private final Logger logger = LoggerFactory.getLogger(PostService.class);

    private Post findActivePostById(UUID id) {
        Post post = postRepository.findByIdAndNotDeleted(id).orElseThrow(
                () -> new PostNotFoundException("Post with id " + id + " not found")
        );
        if (post.isDeleted()) {
            throw new PostNotFoundException("Post with id " + id + " not found");
        }
        return post;
    }

    private List<Post> findAllActivePosts() {
        return postRepository.findAllNotIsDeleted();
    }

    public PostService(PostRepository postRepository,  StorageService storageService) {
        this.postRepository = postRepository;
        this.storageService = storageService;
    }

    public List<PostResponseDTO> findAll() { // TODO replace by recommendation system
        return findAllActivePosts().stream().map(Mapper::toPostResponseDTO).toList();
    }

    public PostResponseDTO getPostById(UUID id) {
        Post post = findActivePostById(id);
        return Mapper.toPostResponseDTO(post);
    }

    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        String contentType = postRequestDTO.getImage().getContentType();
        assert contentType != null;

        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new ContentTypeNotValidException("Content type " + contentType + " is not supported");
        }
        try {
            logger.info("Uploading image to cloud...");
            String imageUrl = storageService.uploadFile(postRequestDTO.getImage());

            Post post = Mapper.toPost(postRequestDTO);
            post.setDeleted(false);
            post.setEdited(false);
            post.setImageUrl(imageUrl);
            post.setViewCount(0);
            post.setCommentCount(0);
            post.setLikeCount(0);
            post.setPostedAt(Instant.now());
            post.setUpdatedAt(Instant.now());

            Post savedPost = postRepository.save(post);

            logger.info("Saved new post to database");
            return Mapper.toPostResponseDTO(savedPost);

        } catch (IOException e) {
            throw new ImageUploadFailedException("Failed to upload image");
        }
    }

    public PostResponseDTO updatePost(UUID id, PostRequestDTO postRequestDTO) {
        String contentType = postRequestDTO.getImage().getContentType();
        assert contentType != null;

        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new ContentTypeNotValidException("Content type " + contentType + " is not supported");
        }
        Post post = findActivePostById(id);

        try {
            storageService.updateFile(post.getImageUrl(), postRequestDTO.getImage());
            post.setEdited(true);
            post.setDescription(postRequestDTO.getDescription());

            post.setLocationName(postRequestDTO.getLocationName());
            post.setLatitude(postRequestDTO.getLatitude());
            post.setLongitude(postRequestDTO.getLongitude());

            post.setDescription(postRequestDTO.getDescription());
            post.setPublicVisible(postRequestDTO.isPubliclyVisible());
            return Mapper.toPostResponseDTO(postRepository.save(post));
        } catch (IOException e) {
            throw new ImageUploadFailedException("Failed to upload image");
        }
    }

    public void deletePostById(UUID id) {
        Post post = findActivePostById(id);
        post.setDeleted(true);
        post.setDeletedAt(Instant.now());
        postRepository.save(post);
    }
}
