package com.tenderflex.tenderflex.service;
import com.tenderflex.tenderflex.models.User;
import java.util.List;

public interface UserService {
    User create(User user);
    List<User> getAllUsers();
}
