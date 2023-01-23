package com.tenderflex.tenderflex.controllers;

import com.tenderflex.tenderflex.models.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final JdbcTemplate jdbcTemplate;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAllUsers() {
        System.out.println("getAllUsers");
        return ResponseEntity.ok("getUsers");
    }

    @PostMapping
    public void registerNewUser(@RequestBody ApplicationUser user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody ApplicationUser user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
}
