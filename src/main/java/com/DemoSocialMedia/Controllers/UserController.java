package com.DemoSocialMedia.Controllers;

import com.DemoSocialMedia.Models.User;
import com.DemoSocialMedia.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
