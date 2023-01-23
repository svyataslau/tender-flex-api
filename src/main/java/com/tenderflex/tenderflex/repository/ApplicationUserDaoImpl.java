package com.tenderflex.tenderflex.repository;

import com.tenderflex.tenderflex.converter.ApplicationUserConverter;
import com.tenderflex.tenderflex.models.ApplicationUserDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    public final JdbcTemplate jdbcTemplate;
    public ApplicationUserConverter applicationUserConverter;

    @Override
    public ApplicationUserDto selectApplicationUserByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE username=?", BeanPropertyRowMapper.newInstance(ApplicationUserDto.class), username);
    }
}
