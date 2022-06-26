package com.vti.brse.controller;

import com.vti.brse.entity.UserEntity;
import com.vti.brse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    //This is new line
    @GetMapping
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/{userId}")
    public Optional<UserEntity> findUserById(@PathVariable Integer userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/email/{email}")
    public List<UserEntity> findUserById(@PathVariable String email) {
        return userService.findAllUsersByEmail(email);
    }

    @GetMapping("/login")
    public List<UserEntity> findUserById(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
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
