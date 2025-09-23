package org.example.pimob.application.useCases.auth;

import org.example.pimob.communication.request.AuthRequest;
import org.example.pimob.communication.response.AuthResponse;
import org.example.pimob.domain.enums.PermissionsEnum;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.config.jwt.JwtTokenProvider;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUseCase implements ILoginUseCase {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginUseCase(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AuthResponse execute(AuthRequest authRequest) {

    var user = userRepository.findByEmail(authRequest.email()).orElseThrow(() -> new UserNotFoundException("Usuário ou senha inválidos"));


    if (!passwordEncoder.matches(authRequest.password(), user.getSenha())) {
      throw new UserNotFoundException("Usuário ou senha inválidos");
    }

    String jwt = jwtTokenProvider.generateToken(user.getEmail(), user.getTeams().toString());

    return new AuthResponse(
            jwt,
            1,
            authRequest.email(),
            List.of(
                    PermissionsEnum.PROPERTY_ADD,
                    PermissionsEnum.USER_CREATE_CLIENT,
                    PermissionsEnum.USER_CREATE_CLIENT
            )
    );
  }
}
