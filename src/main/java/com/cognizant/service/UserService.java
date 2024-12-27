package com.cognizant.service;


import org.springframework.stereotype.Service;
import com.cognizant.entity.User;
import com.cognizant.exception.InvalidUserInputException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.util.UserUtil;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static List<User> users = UserUtil.getInitialUsers();
    public void createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidUserInputException("Username cannot be empty");
        }
        users.add(user);
    }

    public void updateUser(User user) {
        if (user.getEmail() == null || user.getPhoneNumber() == null) {
            throw new InvalidUserInputException("Email and phone number cannot be null");
        }
        Optional<User> existingUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + user.getUsername());
        }
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPhoneNumber(user.getPhoneNumber());
    }

    public User getUser(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    public void deleteUser(String username) {
        Optional<User> existingUser = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        users.remove(existingUser.get());
    }

    public List<User> getAllUser() {
        return users;
    }
}

