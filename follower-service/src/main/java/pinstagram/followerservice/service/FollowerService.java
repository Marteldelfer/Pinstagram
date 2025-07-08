package pinstagram.followerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pinstagram.followerservice.dto.FollowRequestDto;
import pinstagram.followerservice.dto.UserResponseDto;
import pinstagram.followerservice.exception.UserNotFoundException;
import pinstagram.followerservice.model.User;
import pinstagram.followerservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FollowerService {

    private final UserRepository userRepository;

    public FollowerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getFollows(UUID id) {
        log.info("getFollows request with id: {}", id);
        return userRepository.findFollows(id).stream().map(UserResponseDto::fromUser).toList();
    }

    public List<UserResponseDto> getFollowers(UUID id) {
        log.info("getFollowers request with id: {}", id);
        return userRepository.findFollowers(id).stream().map(UserResponseDto::fromUser).toList();
    }

    public void follow(FollowRequestDto requestDto) {
        log.info("follow request: {}", requestDto);
        User follower = userRepository.findById(requestDto.getFollowerId()).orElseGet(
                () -> userRepository.save(new User(requestDto.getFollowerId()))
        );
        User followed = userRepository.findById(requestDto.getFollowedId()).orElseGet(
                () -> userRepository.save(new User(requestDto.getFollowedId()))
        );
        follower.getFollows().add(followed);
        userRepository.save(follower);
    }

    public void unfollow(FollowRequestDto requestDto) {
        log.info("unfollow request: {} to {}", requestDto.getFollowerId(),  requestDto.getFollowedId());
        User follower = userRepository.findById(requestDto.getFollowerId()).orElseThrow(
                () -> new UserNotFoundException("Follower user with id " + requestDto.getFollowerId() + " not found")
        );
        follower.getFollows().removeIf(u -> requestDto.getFollowedId().equals(u.getId()));
        userRepository.save(follower);
    }


}
