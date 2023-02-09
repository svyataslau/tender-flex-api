package com.tenderflex.tenderflex.repository;

import com.tenderflex.tenderflex.models.ApplicationUserDto;

public interface ApplicationUserDao {
    ApplicationUserDto selectApplicationUserByUsername(String username);
}
