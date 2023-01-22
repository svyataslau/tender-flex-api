package com.tenderflex.tenderflex.repository;

import com.tenderflex.tenderflex.models.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    public User save(User user) {
        jdbcTemplate.update("INSERT INTO user_profile (username, email, password) VALUES(?,?,?)",
                new Object[]{user.getUsername(), user.getEmail(), user.getPassword()});

        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE email=?",
                BeanPropertyRowMapper.newInstance(User.class), user.getEmail());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from user_profile", BeanPropertyRowMapper.newInstance(User.class));
    }

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE username=?",
                BeanPropertyRowMapper.newInstance(User.class), username);
    }
}
