package pinstagram.followerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pinstagram.followerservice.model.User;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponseDto {

    private UUID id;
    private Instant createdAt;

    public static UserResponseDto fromUser(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
