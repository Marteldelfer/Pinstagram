package com.pinstagram.accountservice.controller;

import com.pinstagram.accountservice.dto.AccountEditPicDto;
import com.pinstagram.accountservice.dto.AccountEditRequestDto;
import com.pinstagram.accountservice.dto.AccountRequestDto;
import com.pinstagram.accountservice.dto.AccountResponseDto;
import com.pinstagram.accountservice.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.createAccount(accountRequestDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(
            @PathVariable UUID id,
            @Valid @RequestBody AccountEditRequestDto requestDto
    ) {
        return ResponseEntity.ok(accountService.updateAccountDetails(id, requestDto));
    }

    @PutMapping("/pic/{id}")
    public ResponseEntity<AccountResponseDto> updateAccountPic(
            @PathVariable UUID id,
            @Valid @ModelAttribute AccountEditPicDto requestDto
    ) {
        return ResponseEntity.ok(accountService.updatePic(id, requestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

}
