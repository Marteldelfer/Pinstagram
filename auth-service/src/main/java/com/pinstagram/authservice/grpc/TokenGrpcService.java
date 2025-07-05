package com.pinstagram.authservice.grpc;

import com.pinstagram.authservice.service.UserService;
import com.pinstagram.authservice.util.JwtUtil;
import com.pinstagram.tokenservice.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class TokenGrpcService extends TokenServiceGrpc.TokenServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(TokenGrpcService.class);
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public TokenGrpcService(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public void getUnvalidatedToken(
            AccountDetails request,
            StreamObserver<TokenResponse> responseObserver
    ) {
        logger.info("Generating new unvalidated token");
        String token = jwtUtil.generateUnvalidatedToken(request);
        TokenResponse response = TokenResponse.newBuilder()
                .setUnvalidatedToken(token).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createAuthUser(
            CreateAuthUserRequest request,
            StreamObserver<Empty> responseObserver
    ) {
        logger.info("Creating new auth user");
        userService.createUser(request);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
