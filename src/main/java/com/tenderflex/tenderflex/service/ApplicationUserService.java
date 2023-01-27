package com.tenderflex.tenderflex.service;

import com.tenderflex.tenderflex.converter.ApplicationUserConverter;
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
            return applicationUserConverter.convertToEntity(
                    applicationUserDao.selectApplicationUserByUsername(username)
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("Username %s not found in db", username));
        }
    }
}
