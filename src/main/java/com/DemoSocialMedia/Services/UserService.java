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

    public ResponseEntity<?> loginUser(User user) {
        User foundUser = userRepo.findByUsername(user.getUsername());
        if (!foundUser.isUserActive()) {
            return new ResponseEntity<>("User not active",HttpStatus.BAD_REQUEST);
        }
        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        } else {
            foundUser.setLoggedIn(true);
            userRepo.save(foundUser);
            return new ResponseEntity<>("Welcome, " + foundUser.getFirstName() + "! You are logged in.", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> logoutUser(long id) {
        User loggedInUser = userRepo.findById(id).get();
        loggedInUser.setLoggedIn(false);
        userRepo.save(loggedInUser);
        return new ResponseEntity<>("Goodbye, " + loggedInUser.getFirstName() + "! You have logged out.", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(long id) {
        User loggedInUser = userRepo.findById(id).get();
        if (!loggedInUser.isLoggedIn()) {
            return new ResponseEntity<>("User not found or user not logged in", HttpStatus.NOT_FOUND);
        } else {
            userRepo.delete(loggedInUser);
            return new ResponseEntity<>("Username: " + loggedInUser.getUsername() +  ", deleted", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deactivateUser(long id) {
        User loggedInUser = userRepo.findById(id).get();
        if (!loggedInUser.isLoggedIn()) {
            return new ResponseEntity<>("User not found or user not logged in", HttpStatus.NOT_FOUND);
        } else {
            loggedInUser.setUserActive(false);
            userRepo.save(loggedInUser);
            return new ResponseEntity<>("Username: " + loggedInUser.getUsername() +  ", has been deactivated", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> reactivateUser(User user) {
        User foundUser = userRepo.findByUsername(user.getUsername());
        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        } else {
            foundUser.setUserActive(true);
            userRepo.save(foundUser);
            return new ResponseEntity<>("Username: " + foundUser.getUsername() + ", has been reactivated", HttpStatus.OK);
        }
    }
}
