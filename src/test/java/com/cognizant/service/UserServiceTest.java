package com.cognizant.service;


import com.cognizant.entity.User;
import com.cognizant.exception.InvalidUserInputException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private List<User> mockUsers;

    @BeforeEach
    void setUp() {
        mockUsers = new ArrayList<>(UserUtil.getInitialUsers());
        userService = new UserService();
        resetInitialUsers();
    }

    private void resetInitialUsers() {
        List<User> initialUsers = UserUtil.getInitialUsers();
        initialUsers.clear();
        initialUsers.addAll(mockUsers);
    }


    @Test
    void testCreateUserInvalidUser() {
        User invalidUser = new User(null, "xyz@gmail.com", "1234567890");
        assertThrows(InvalidUserInputException.class, () -> userService.createUser(invalidUser),
                "InvalidUserInputException should be thrown when username is null");
    }

  
    @Test
    void testUpdateUserUserNotFound() {
        User nonExistentUser = new User("nonexistent", "abc@gmail.com", "1234567890");
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(nonExistentUser),
                "UserNotFoundException should be thrown when user does not exist");
    }


    @Test
    void testGetUserUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getUser("nonexistent"),
                "UserNotFoundException should be thrown when user does not exist");
    }

    @Test
    void testDeleteUserUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser("nonexistent"),
                "UserNotFoundException should be thrown when user does not exist");
    }

    @Test
    void testGetUserList() {
        List<User> users = userService.getAllUser();
        assertEquals(mockUsers, users, "The list of users should match the mock data");
    }
}
