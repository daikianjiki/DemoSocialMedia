package com.DemoSocialMedia.Controllers;

import com.DemoSocialMedia.Models.User;
import com.DemoSocialMedia.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PatchMapping("logout/{id}")
    public ResponseEntity<?> logoutUser(@PathVariable long id) {
        return userService.logoutUser(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateUser(@PathVariable long id) {
        return userService.deactivateUser(id);
    }

    @PatchMapping("reactivate")
    public ResponseEntity<?> reactivateUser(@RequestBody User user) {
        return userService.reactivateUser(user);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllUsers() {
        return userService.getAllUsers();
    }
}
