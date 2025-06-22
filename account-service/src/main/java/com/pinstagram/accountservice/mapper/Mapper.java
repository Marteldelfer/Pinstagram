package com.pinstagram.accountservice.mapper;

import com.pinstagram.accountservice.dto.AccountRequestDto;
import com.pinstagram.accountservice.dto.AccountResponseDto;
import com.pinstagram.accountservice.model.Account;

public class Mapper {

    public static Account toModel(AccountRequestDto requestDto) {
        return Account.builder()
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .username(requestDto.getUsername())
                .build();
    }

    public static AccountResponseDto toDto(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .name(account.getName())
                .username(account.getUsername())
                .avatarUrl(account.getAvatarUrl())
                .bio(account.getBio())
                .createdAt(account.getCreatedAt())
                .publicationCount(account.getPublicationCount())
                .followerCount(account.getFollowerCount())
                .followingCount(account.getFollowingCount())
                .isPrivate(account.isPrivate())
                .build();
    }


}
