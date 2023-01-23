package com.tenderflex.tenderflex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.tenderflex.tenderflex.config.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
// Keep in mind: without EnableGlobalMethodSecurity @PreAuthorize in controller will not protect endpoints
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails bidderUser = User.builder()
            .username("bidder")
            .password(passwordEncoder.encode("password"))
            .authorities(BIDDER.getGrantedAuthorities())
            .build();

    UserDetails adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("password"))
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

    UserDetails contractorUser = User.builder()
            .username("contractor")
            .password(passwordEncoder.encode("password"))
            .authorities(CONTRACTOR.getGrantedAuthorities())
            .build();

    return new InMemoryUserDetailsManager(
            bidderUser,
            adminUser,
            contractorUser
    );

  }

}