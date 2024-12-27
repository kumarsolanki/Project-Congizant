package com.cognizant.util;

import java.util.ArrayList;
import java.util.List;
import com.cognizant.entity.User;

public class UserUtil {
    private static List<User> users = new ArrayList<>();
    public static List<User> getInitialUsers() {
        if (users.isEmpty()) {
            users.add(new User("user1", "user1@gmail.com", "1234567890"));
            users.add(new User("user2", "user2@gmail.com", "0987654321"));
        }
        return users;
    }
}
