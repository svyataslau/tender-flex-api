package com.tenderflex.tenderflex.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@AllArgsConstructor
@Data
public class ApplicationUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Set<SimpleGrantedAuthority> authorities;
}