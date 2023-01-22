package com.tenderflex.tenderflex.controllers;

import com.tenderflex.tenderflex.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class UserController {

  public final JdbcTemplate jdbcTemplate;

  @GetMapping("/users")
  public ResponseEntity<Collection<User>> getUsers() {
    return ResponseEntity.ok(jdbcTemplate.query("SELECT * from user_profile", BeanPropertyRowMapper.newInstance(User.class)));
  }

  @PostMapping("/users")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    return ResponseEntity.ok(user);
  }
}
