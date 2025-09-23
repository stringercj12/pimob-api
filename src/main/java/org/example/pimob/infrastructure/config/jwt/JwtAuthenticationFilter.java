package org.example.pimob.infrastructure.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pimob.application.useCases.auth.PermissionUseCase;
import org.example.pimob.application.useCases.user.getByEmail.UserGetByEmailUseCase;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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


  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, PermissionUseCase permissionUseCase, UserGetByEmailUseCase userGetByEmailUseCase) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.permissionUseCase = permissionUseCase;
    this.userGetByEmailUseCase = userGetByEmailUseCase;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      String jwt = getJwtFromRequest(request);

      if (jwt != null && !jwt.isEmpty()) {
        String email = jwtTokenProvider.getEmailFromToken(jwt);

        var userDetails = userGetByEmailUseCase.execute(email);

        System.out.println("Filter: " + userDetails.toString());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }

    filterChain.doFilter(request, response);

  }

  private String getJwtFromRequest(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }

    return null;
  }

}
