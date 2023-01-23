package com.tenderflex.tenderflex.converter;

import com.tenderflex.tenderflex.models.ApplicationUser;
import com.tenderflex.tenderflex.models.ApplicationUserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.tenderflex.tenderflex.config.ApplicationUserRole.ADMIN;

@Component
@AllArgsConstructor
public class ApplicationUserConverter implements Converter<ApplicationUserDto, ApplicationUser> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ApplicationUser> convertToEntities(Collection<ApplicationUserDto> userDtos) {
        return userDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public ApplicationUser convertToEntity(ApplicationUserDto userDto) {
        return new ApplicationUser(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
    }

    @Override
    public ApplicationUserDto convertToDto(ApplicationUser user) {
        ApplicationUserDto userDto = new ApplicationUserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
