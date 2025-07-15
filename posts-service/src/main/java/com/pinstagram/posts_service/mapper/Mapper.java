package com.pinstagram.posts_service.mapper;

import com.pinstagram.posts_service.dto.PostRequestDTO;
import com.pinstagram.posts_service.dto.PostResponseDTO;
import com.pinstagram.posts_service.model.Post;

public class Mapper {

    public static PostResponseDTO toPostResponseDTO(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .imageUrl(post.getImageUrl())
                .description(post.getDescription())
                .viewCount(post.getViewCount())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .isEdited(post.isEdited())
                .postedAt(post.getPostedAt())
                .locationName(post.getLocationName())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .build();
    }
    public static Post toPost(PostRequestDTO postRequestDTO) {
        return Post.builder()
                .description(postRequestDTO.getDescription())
                .isPublicVisible(postRequestDTO.isPubliclyVisible())
                .locationName(postRequestDTO.getLocationName())
                .latitude(postRequestDTO.getLatitude())
                .longitude(postRequestDTO.getLongitude())
                .build();
    }
}
