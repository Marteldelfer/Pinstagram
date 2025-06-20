package com.pinstagram.posts_service.grpc;

import com.pinstagram.posts_service.repositories.PostRepository;
import com.pinstagram.postservice.PostExistsRequest;
import com.pinstagram.postservice.PostExistsResponse;
import com.pinstagram.postservice.PostValidationServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class PostGrpcService extends PostValidationServiceGrpc.PostValidationServiceImplBase {

    private final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostGrpcService.class);

    public PostGrpcService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void checkPostExists(
            PostExistsRequest request,
            StreamObserver<PostExistsResponse> responseObserver
    ) {
        UUID postId = UUID.fromString(request.getPostId());
        boolean exists = postRepository.existsByIdAndDeletedFalse(postId);
        logger.info("checkPostExists postId:{}, exists:{}", postId, exists);

        PostExistsResponse response = PostExistsResponse.newBuilder()
                .setPostExists(exists).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
