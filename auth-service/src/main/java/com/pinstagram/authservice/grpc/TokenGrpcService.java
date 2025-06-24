package com.pinstagram.authservice.grpc;

import com.pinstagram.authservice.util.JwtUtil;
import com.pinstagram.tokenservice.TokenRequest;
import com.pinstagram.tokenservice.TokenServiceGrpc;
import com.pinstagram.tokenservice.UnvalidatedTokenResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class TokenGrpcService extends TokenServiceGrpc.TokenServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(TokenGrpcService.class);
    private final JwtUtil jwtUtil;

    public TokenGrpcService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void getUnvalidatedToken(
            TokenRequest request,
            StreamObserver<UnvalidatedTokenResponse> responseObserver
    ) {
        logger.info("Generating new unvalidated token");
        String token = jwtUtil.generateUnvalidatedToken(request);
        UnvalidatedTokenResponse response = UnvalidatedTokenResponse.newBuilder()
                .setUnvalidatedToken(token).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
