package com.pinstagram.accountservice.grpc;

import com.pinstagram.accountservice.model.Account;
import com.pinstagram.tokenservice.AccountDetails;
import com.pinstagram.tokenservice.TokenResponse;
import com.pinstagram.tokenservice.TokenServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenGrpcClient {

    private static final Logger logger = LoggerFactory.getLogger(TokenGrpcClient.class);
    private final TokenServiceGrpc.TokenServiceBlockingStub blockingStub;

    public TokenGrpcClient(
            @Value("${token.service.address:localhost}") String serverAddress,
            @Value("${token.service.port:9005}") int serverPort
    ) {
        logger.info("Connecting to PostValidation Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();
        blockingStub = TokenServiceGrpc.newBlockingStub(channel);
    }

    public String getUnvalidatedToken(Account account) {
        AccountDetails tokenRequest = AccountDetails.newBuilder()
                .setId(account.getId().toString())
                .setEmail(account.getEmail())
                .setName(account.getName())
                .setUsername(account.getUsername())
                .build();
        TokenResponse response =  blockingStub.getUnvalidatedToken(tokenRequest);
        return response.getUnvalidatedToken();
    }
}
