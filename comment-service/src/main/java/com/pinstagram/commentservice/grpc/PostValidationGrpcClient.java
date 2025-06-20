package com.pinstagram.commentservice.grpc;

import com.pinstagram.postservice.PostExistsRequest;
import com.pinstagram.postservice.PostExistsResponse;
import com.pinstagram.postservice.PostValidationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostValidationGrpcClient {

    private static final Logger logger = LoggerFactory.getLogger(PostValidationGrpcClient.class);
    private final PostValidationServiceGrpc.PostValidationServiceBlockingStub blockingStub;

    public PostValidationGrpcClient(
            @Value("${post.validation.service.address}") String serverAddress,
            @Value("${post.validation.service.port}") int serverPort
    ) {
        logger.info("Connecting to PostValidation Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();
        blockingStub = PostValidationServiceGrpc.newBlockingStub(channel);
    }

    public boolean checkPostExists(UUID postId) {
        PostExistsRequest request = PostExistsRequest.newBuilder()
                .setPostId(postId.toString()).build();
        PostExistsResponse response = blockingStub.checkPostExists(request);
        logger.info("Received post exists response: {}", response.getPostExists());
        return response.getPostExists();
    }

}
