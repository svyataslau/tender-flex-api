package com.tenderflex.tenderflex.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleAccessDto {
    private String role;
    private String permission;
}
