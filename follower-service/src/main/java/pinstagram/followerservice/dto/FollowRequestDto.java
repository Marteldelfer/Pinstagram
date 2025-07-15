package pinstagram.followerservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FollowRequestDto {

    @NotNull
    private UUID followedId;

}
