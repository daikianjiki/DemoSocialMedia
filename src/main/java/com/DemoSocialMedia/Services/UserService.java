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
        if (userRepo.existsByEmail(email)) {
            return new ResponseEntity<String>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }
}
