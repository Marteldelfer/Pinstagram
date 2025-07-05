package com.pinstagram.accountservice.grpc;


import com.pinstagram.accountservice.model.Account;
import com.pinstagram.accountservice.service.AccountService;
import com.pinstagram.tokenservice.AccountDetails;
import com.pinstagram.tokenservice.AccountDetailsRequest;
import com.pinstagram.tokenservice.TokenServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AccountDetailsGrpcService extends TokenServiceGrpc.TokenServiceImplBase {
    private final AccountService accountService;

    public AccountDetailsGrpcService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void getAccountDetails(
            AccountDetailsRequest request,
            StreamObserver<AccountDetails> responseObserver
    ) {
        sendAccountDetails(request, responseObserver);
    }

    @Override
    public void verifyAccount(
            AccountDetailsRequest request,
            StreamObserver<AccountDetails> responseObserver
    ) {
        accountService.verifyAccount(request.getEmail());
        sendAccountDetails(request, responseObserver);
    }

    private void sendAccountDetails(AccountDetailsRequest request, StreamObserver<AccountDetails> responseObserver) {
        Account account = accountService.findByEmail(request.getEmail());
        AccountDetails accountDetails = AccountDetails.newBuilder()
                .setId(account.getId().toString())
                .setEmail(account.getEmail())
                .setName(account.getName())
                .setUsername(account.getUsername())
                .setValidated(account.getValidated())
                .build();
        responseObserver.onNext(accountDetails);
        responseObserver.onCompleted();
    }
}
