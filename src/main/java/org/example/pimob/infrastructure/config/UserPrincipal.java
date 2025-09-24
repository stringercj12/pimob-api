package org.example.pimob.infrastructure.config;

import lombok.Getter;
import org.example.pimob.domain.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal {

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  private UserPrincipal(UserEntity user) {
    this.username = user.getEmail();
    this.password = user.getSenha();

    this.authorities = user.getTeams().stream().map(team -> {
      return new SimpleGrantedAuthority("ROLE_".concat(team.getName()));
    }).collect(Collectors.toList());
  }


  public static UserPrincipal create(UserEntity user) {
    return new UserPrincipal(user);
  }
}
