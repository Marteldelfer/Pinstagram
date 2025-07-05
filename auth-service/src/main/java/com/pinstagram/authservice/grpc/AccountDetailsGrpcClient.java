package com.pinstagram.authservice.grpc;

import com.pinstagram.tokenservice.AccountDetails;
import com.pinstagram.tokenservice.AccountDetailsRequest;
import com.pinstagram.tokenservice.TokenServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsGrpcClient {

    private static final Logger logger = LoggerFactory.getLogger(AccountDetailsGrpcClient.class);
    private final TokenServiceGrpc.TokenServiceBlockingStub blockingStub;

    public AccountDetailsGrpcClient(
            @Value("${token.service.address:localhost}") String serverAddress,
            @Value("${token.service.port:9003}") int serverPort
    ) {
        logger.info("Connecting to PostValidation Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();
        blockingStub = TokenServiceGrpc.newBlockingStub(channel);
    }

    public AccountDetails getAccountDetails(String email) {
        AccountDetailsRequest request = AccountDetailsRequest.newBuilder()
                .setEmail(email).build();
        return blockingStub.getAccountDetails(request);
    }

    public AccountDetails verifyAccount(String email) {
        AccountDetailsRequest request = AccountDetailsRequest.newBuilder()
                .setEmail(email).build();
        return blockingStub.verifyAccount(request);
    }
}