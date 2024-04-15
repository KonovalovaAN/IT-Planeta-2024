package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.User;

public interface UserService {
    User createUser(User user);
    User authenticateUser(String email, String password);
    User getUserById(Integer userId);
    User updateUser(Integer userId, User user);
    void deleteUser(Integer userId);
}
