package pinstagram.followerservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pinstagram.followerservice.dto.FollowRequestDto;
import pinstagram.followerservice.dto.UserResponseDto;
import pinstagram.followerservice.service.FollowerService;

import java.util.List;
import java.util.UUID;

@RestController
public class FollowerController {

    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(followerService.getFollowers(id));
    }

    @GetMapping("/{id}/follows")
    public ResponseEntity<List<UserResponseDto>> getFollows(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(followerService.getFollows(id));
    }

    @PostMapping
    public ResponseEntity<Void> follow(@Valid @RequestBody FollowRequestDto followRequestDto) {
        followerService.follow(followRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unfollow(@Valid @RequestBody FollowRequestDto followRequestDto) {
        followerService.unfollow(followRequestDto);
        return ResponseEntity.ok().build();
    }
}
