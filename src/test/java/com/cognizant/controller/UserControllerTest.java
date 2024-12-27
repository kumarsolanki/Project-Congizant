package com.cognizant.controller;

import com.cognizant.entity.User;
import com.cognizant.service.UserService;
import com.cognizant.util.UserConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testuser1");
        user.setEmail("testuser1@gmail.com");
        doNothing().when(userService).createUser(any(User.class));
        mockMvc.perform(post(UserConstant.BASE_URL + UserConstant.CREATE_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(UserConstant.USER_CREATED));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setUsername("updateduser1");
        user.setEmail("updateduser1@gmail.com");
        doNothing().when(userService).updateUser(any(User.class));
        mockMvc.perform(put(UserConstant.BASE_URL + UserConstant.UPDATE_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(UserConstant.USER_UPDATED));

        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@gmail.com");
        when(userService.getUser("testuser")).thenReturn(user);
        mockMvc.perform(get(UserConstant.BASE_URL + UserConstant.GET_USER, "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@gmail.com"));

        verify(userService, times(1)).getUser("testuser");
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser("user1");
        mockMvc.perform(delete(UserConstant.BASE_URL + "/{username}", "user1"))
                .andExpect(status().isOk())
                .andExpect(content().string(UserConstant.USER_DELETED));

        verify(userService, times(1)).deleteUser("user1");
    }

    @Test
    public void testGetUserList() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@gmail.com");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@gmail.com");
        List<User> userList = Arrays.asList(user1, user2);
        when(userService.getAllUser()).thenReturn(userList);
        mockMvc.perform(get(UserConstant.BASE_URL + UserConstant.GET_USER_LIST))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].email").value("user1@gmail.com"))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[1].email").value("user2@gmail.com"));

        verify(userService, times(1)).getAllUser();
    }
}
