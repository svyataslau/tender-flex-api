package com.tenderflex.tenderflex.config;

import com.tenderflex.tenderflex.auth.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
// Keep in mind: without EnableGlobalMethodSecurity @PreAuthorize in controller will not protect endpoints
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;

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
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);
    return provider;
  }

//  @Override
//  @Bean
//  protected UserDetailsService userDetailsService() {
//    UserDetails bidderUser = User.builder()
//            .username("bidder")
//            .password(passwordEncoder.encode("password"))
//            .authorities(BIDDER.getGrantedAuthorities())
//            .build();
//
//    UserDetails adminUser = User.builder()
//            .username("admin")
//            .password(passwordEncoder.encode("password"))
//            .authorities(ADMIN.getGrantedAuthorities())
//            .build();
//
//    UserDetails contractorUser = User.builder()
//            .username("contractor")
//            .password(passwordEncoder.encode("password"))
//            .authorities(CONTRACTOR.getGrantedAuthorities())
//            .build();
//
//    return new InMemoryUserDetailsManager(
//            bidderUser,
//            adminUser,
//            contractorUser
//    );
//
//  }

}