package com.pinstagram.accountservice.service;

import com.pinstagram.accountservice.dto.AccountEditPicDto;
import com.pinstagram.accountservice.dto.AccountEditRequestDto;
import com.pinstagram.accountservice.dto.AccountRequestDto;
import com.pinstagram.accountservice.dto.AccountResponseDto;
import com.pinstagram.accountservice.exception.*;
import com.pinstagram.accountservice.grpc.TokenGrpcClient;
import com.pinstagram.accountservice.kafka.KafkaProducer;
import com.pinstagram.accountservice.mapper.Mapper;
import com.pinstagram.accountservice.model.Account;
import com.pinstagram.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final StorageService storageService;
    private final KafkaProducer kafkaProducer;
    private final TokenGrpcClient tokenGrpcClient;

    public AccountService(
            AccountRepository accountRepository,
            StorageService storageService,
            KafkaProducer kafkaProducer,
            TokenGrpcClient tokenGrpcClient
    ) {
        this.accountRepository = accountRepository;
        this.storageService = storageService;
        this.kafkaProducer = kafkaProducer;
        this.tokenGrpcClient = tokenGrpcClient;
    }

    public AccountResponseDto findById(UUID id) {
        Account account  = accountRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new UserAccountNotFoundException("Account with id " + id + " not found")
        );
        return Mapper.toDto(account);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmailAndDeletedFalse(email).orElseThrow(
                () -> new UserAccountNotFoundException("Account with email " + email + " not found")
        );
    }

    public List<AccountResponseDto> findAll() {
        return accountRepository.findAllByDeletedFalse().stream().map(Mapper::toDto).toList();
    }

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) { // TODO implement email validation and integrate with auth
        logger.info("Creating account with from request with username {}", accountRequestDto.getUsername());
        if (accountRepository.existsByUsername(accountRequestDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Account with name " + accountRequestDto.getUsername() + " Already exists");
        }
        Account account = Mapper.toModel(accountRequestDto);
        account.setDeleted(false);
        account.setCreatedAt(Instant.now());
        account.setFollowerCount(0);
        account.setFollowingCount(0);
        account.setPublicationCount(0);
        account.setIsPrivate(true);
        account.setValidated(false);
        account = accountRepository.save(account);

        logger.info("Account with id {} has been created", account.getId());

        String unvalidatedToken = tokenGrpcClient.getUnvalidatedToken(account);
        kafkaProducer.sendConfirmationEmail(account, unvalidatedToken);

        return Mapper.toDto(account);
    }

    public AccountResponseDto updateAccountDetails(UUID id, AccountEditRequestDto accountRequestDto) {
        logger.info("Updating account with bio: {}; and isPrivate: {};", accountRequestDto.getBio(), accountRequestDto.getIsPrivate());
        Account account = accountRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new UserAccountNotFoundException("Account with id " + id + " not found")
        );
        account.setBio(accountRequestDto.getBio());
        account.setIsPrivate(accountRequestDto.getIsPrivate());
        account.setUpdatedAt(Instant.now());

        return Mapper.toDto(accountRepository.save(account));
    }

    public AccountResponseDto updatePic(UUID id, AccountEditPicDto requestDto) {
        logger.info("Updating user pic");
        String contentType = requestDto.getImage().getContentType();
        assert contentType != null;

        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new ContentTypeNotValidException("Content type " + contentType + " is not supported");
        }
        Account account = accountRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new UserAccountNotFoundException("Account with id " + id + " not found")
        );

        try {
            BufferedImage image = ImageIO.read(requestDto.getImage().getInputStream());
            if (image.getWidth() != image.getHeight()) {
                throw new InvalidImageDimensionException("Image width and height must be equal");
            }

            if (account.getAvatarUrl() != null) {
                storageService.updateFile(account.getAvatarUrl(), requestDto.getImage());
            } else {
                String imageUrl = storageService.uploadFile(requestDto.getImage());
                account.setAvatarUrl(imageUrl);
            }
            account.setUpdatedAt(Instant.now());
            return Mapper.toDto(accountRepository.save(account));

        } catch (IOException e) {
            throw new ImageUploadFailedException("Could not convert image to byte array");
        }
    }

    public void deleteAccount(UUID id) {
        logger.info("Deleting account with id {}", id);
        Account account = accountRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new UserAccountNotFoundException("Account with id " + id + " not found")
        );
        account.setDeleted(true);
        account.setDeletedAt(Instant.now());
        accountRepository.save(account);
    }

    public void verifyAccount(String email) {
        logger.info("Verifying account with email {}", email);
        Account account = accountRepository.findByEmailAndDeletedFalse(email).orElseThrow(
                () -> new UserAccountNotFoundException("Account with email " + email + " not found")
        );
        if (account.getValidated()) {
            throw new AccountAlreadyValidatedException("Account with email " + email + " already validated");
        }
        account.setValidated(true);
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }
}
