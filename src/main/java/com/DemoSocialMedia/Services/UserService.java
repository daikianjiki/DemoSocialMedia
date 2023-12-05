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
    public ResponseEntity<User> registerUser(User user) {
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }
}
