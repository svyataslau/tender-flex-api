package com.tenderflex.tenderflex.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PermissionDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String permission;
}
