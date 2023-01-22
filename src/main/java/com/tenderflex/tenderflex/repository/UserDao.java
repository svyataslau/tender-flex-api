package com.tenderflex.tenderflex.repository;

import com.tenderflex.tenderflex.models.User;

import java.util.List;

public interface UserDao {
    User save(User userDto);

    List<User> findAll();

    User findByUsername(String username);
}
