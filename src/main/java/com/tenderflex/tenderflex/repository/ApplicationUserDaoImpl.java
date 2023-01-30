package com.tenderflex.tenderflex.repository;

import com.tenderflex.tenderflex.models.ApplicationUserDto;
import com.tenderflex.tenderflex.models.Authority;
import com.tenderflex.tenderflex.models.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    public final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUserDto selectApplicationUserByUsername(String username) {
        UserDto userDto = this.jdbcTemplate.queryForObject(
                    "SELECT up.*, ur.role\n" +
                            "FROM user_profile up\n" +
                            "JOIN user_role ur\n" +
                            "ON up.user_role_id=ur.id\n" +
                            "WHERE username=?", BeanPropertyRowMapper.newInstance(UserDto.class), username);

        List<Authority> permissions = this.jdbcTemplate.query(
                "SELECT p.title\n" +
                "FROM user_role ur\n" +
                "INNER JOIN user_role_access ura\n" +
                "ON ur.id=ura.user_role_id\n" +
                "JOIN \"permission\" p\n" +
                "ON p.id=ura.permission_id\n" +
                "WHERE ur.role=?", BeanPropertyRowMapper.newInstance(Authority.class), userDto.getRole());

        Set<SimpleGrantedAuthority> userAuthorities = new HashSet<>();

        permissions.stream().forEach(permission -> {
            userAuthorities.add(new SimpleGrantedAuthority(permission.getTitle()));
        });
        userAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userDto.getRole()));
        ApplicationUserDto dto = new ApplicationUserDto(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole(),
                userAuthorities
        );
        return dto;
    }
}
