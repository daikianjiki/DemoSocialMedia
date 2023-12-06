package com.DemoSocialMedia.Services;

import com.DemoSocialMedia.Models.User;
import com.DemoSocialMedia.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public ResponseEntity<?> registerUser(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        if (userRepo.existsByEmail(email) && userRepo.existsByUsername(username)) {
            return new ResponseEntity<>("Email and username already exists", HttpStatus.BAD_REQUEST);
        } else if  (userRepo.existsByEmail(email)) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        } else if (userRepo.existsByUsername(username)) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        }
    }
}
