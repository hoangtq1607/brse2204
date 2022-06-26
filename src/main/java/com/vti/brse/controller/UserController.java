package com.vti.brse.controller;

import com.vti.brse.entity.UserEntity;
import com.vti.brse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<UserEntity> findUserById(@PathVariable Integer userId) {
        return userService.findUserById(userId);
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public Optional<UserEntity> updateUser(@PathVariable Integer userId, @RequestBody UserEntity user) {

        return userService.updateUser(userId, user);

    }

    @DeleteMapping("/{userId}")
    public void deleteByUserId(@PathVariable Integer userId) {
        userService.deleteByUserId(userId);
    }

}
