package com.Twitter.App.controller;

import com.Twitter.App.models.User;
import com.Twitter.App.respositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

}
