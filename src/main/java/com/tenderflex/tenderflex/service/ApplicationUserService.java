package com.tenderflex.tenderflex.service;

import com.tenderflex.tenderflex.converter.ApplicationUserConverter;
import com.tenderflex.tenderflex.models.ApplicationUserDto;
import com.tenderflex.tenderflex.repository.ApplicationUserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;
    private final ApplicationUserConverter applicationUserConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApplicationUserDto dto = applicationUserDao.selectApplicationUserByUsername(username);
            System.out.println(dto.getAuthorities());
            System.out.println(dto.getRole());
            return applicationUserConverter.convertToEntity(
                    dto
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("Username %s not found in db", username));
        }
    }
}
