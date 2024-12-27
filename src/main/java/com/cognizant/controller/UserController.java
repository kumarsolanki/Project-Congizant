package com.cognizant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.entity.User;
import com.cognizant.service.UserService;
import com.cognizant.util.UserConstant;

import java.util.List;

@RestController
@RequestMapping(UserConstant.BASE_URL)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

// End point to create a new user.
    @PostMapping(UserConstant.CREATE_USER)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        logger.info("Received request to create user: {}", user.getUsername());
        userService.createUser(user);
        logger.info("User created successfully: {}", user.getUsername());
        return ResponseEntity.ok(UserConstant.USER_CREATED);
    }

// End point to update an existing user.
    @PutMapping(UserConstant.UPDATE_USER)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        logger.info("Received request to update user with username: {}", user.getUsername());
        userService.updateUser(user);
        logger.info("User updated successfully with username: {}", user.getUsername());
        return ResponseEntity.ok(UserConstant.USER_UPDATED);
    }

// End point to get a user by user name.
    @GetMapping(UserConstant.GET_USER)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        logger.info("Received request to get user with username: {}", username);
        User user = userService.getUser(username);
        logger.info("User retrieved successfully with username: {}", username);
        return ResponseEntity.ok(user);
    }

// End point to delete a user by user name.

    @DeleteMapping(UserConstant.DELETE_USER)
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        logger.info("Received request to delete user with username: {}", username);
        userService.deleteUser(username);
        return ResponseEntity.ok(UserConstant.USER_DELETED);
    }

    // End point to get a user by user name.
    @GetMapping(UserConstant.GET_USER_LIST)
    public ResponseEntity<List<User>> getUser() {
        logger.info("Received request to get users: {}");
        List<User> userList = userService.getAllUser();
        logger.info("User(s) retrieved successfully with {}");
        return ResponseEntity.ok(userList);
    }
}



















