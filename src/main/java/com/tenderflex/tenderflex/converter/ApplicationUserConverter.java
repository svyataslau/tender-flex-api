package com.tenderflex.tenderflex.converter;

import com.tenderflex.tenderflex.models.ApplicationUser;
import com.tenderflex.tenderflex.models.ApplicationUserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ApplicationUserConverter implements Converter<ApplicationUserDto, ApplicationUser>{
  @Override
  public List<ApplicationUser> convertToEntities(Collection<ApplicationUserDto> t) {
    return null;
  }

  @Override
  public ApplicationUser convertToEntity(ApplicationUserDto applicationUserDto) {
    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setId(applicationUserDto.getId());
    applicationUser.setUsername(applicationUserDto.getUsername());
    applicationUser.setEmail(applicationUserDto.getEmail());
    applicationUser.setRole(applicationUserDto.getRole());
    applicationUser.setPassword(applicationUserDto.getPassword());
    applicationUser.setAuthorities(applicationUserDto.getAuthorities());
    return applicationUser;
  }

  @Override
  public ApplicationUserDto convertToDto(ApplicationUser applicationUser) {
    return null;
  }
}
