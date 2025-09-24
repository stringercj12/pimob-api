package org.example.pimob.infrastructure.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pimob.application.useCases.auth.PermissionUseCase;
import org.example.pimob.application.useCases.user.getByEmail.UserGetByEmailUseCase;
import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.user.UserResponse;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.domain.mapping.UserMapping;
import org.example.pimob.exception.auth.AccessDeniedException;
import org.example.pimob.infrastructure.config.UserPrincipal;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final PermissionUseCase permissionUseCase;
  private final UserGetByEmailUseCase userGetByEmailUseCase;
  private final UserRepository userRepository;


  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, PermissionUseCase permissionUseCase, UserGetByEmailUseCase userGetByEmailUseCase, UserRepository userRepository) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.permissionUseCase = permissionUseCase;
    this.userGetByEmailUseCase = userGetByEmailUseCase;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      String jwt = getJwtFromRequest(request);

      if (jwt != null && !jwt.isEmpty()) {
        String email = jwtTokenProvider.getEmailFromToken(jwt);

        UserEntity user = userRepository.findByEmailFetchTeams(email).orElseThrow(() -> new AccessDeniedException("Você não tem acesso a esse serviço"));

        createAuthenticationToken(user);
      }

    filterChain.doFilter(request, response);

  }

  private void createAuthenticationToken(UserEntity user) {
    UserPrincipal userPrincipal = UserPrincipal.create(user);

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }


  private String getJwtFromRequest(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }

    return null;
  }

}
