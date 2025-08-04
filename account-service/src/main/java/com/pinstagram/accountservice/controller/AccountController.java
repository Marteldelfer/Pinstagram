package com.pinstagram.accountservice.controller;

import com.pinstagram.accountservice.dto.AccountEditPicDto;
import com.pinstagram.accountservice.dto.AccountEditRequestDto;
import com.pinstagram.accountservice.dto.AccountRequestDto;
import com.pinstagram.accountservice.dto.AccountResponseDto;
import com.pinstagram.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Tag(name = "Account", description = "API for managing accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @Operation(summary = "Get all accounts")
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get account by id")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create new account")
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.createAccount(accountRequestDto));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update account details")
    public ResponseEntity<AccountResponseDto> updateAccount(
            @PathVariable UUID id,
            @Valid @RequestBody AccountEditRequestDto requestDto
    ) {
        return ResponseEntity.ok(accountService.updateAccountDetails(id, requestDto));
    }

    @PutMapping("/pic/{id}")
    @Operation(summary = "Update account picture")
    public ResponseEntity<AccountResponseDto> updateAccountPic(
            @PathVariable UUID id,
            @Valid @ModelAttribute AccountEditPicDto requestDto
    ) {
        return ResponseEntity.ok(accountService.updatePic(id, requestDto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete account by id")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{search}")
    @Operation(summary = "Search account by name or username")
    public ResponseEntity<List<AccountResponseDto>> searchAccounts(@PathVariable("search") String search) {
        return ResponseEntity.ok(accountService.searchAccountsByUsernameOrName(search));
    }


}
