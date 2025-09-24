package org.example.pimob.application.useCases.user.update;

import jakarta.transaction.Transactional;
import org.example.pimob.communication.response.user.UserRequest;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUpdateUseCase implements IUserUpdateUseCase {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserUpdateUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void execute(UserRequest request, UUID id) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    user.setEmail(request.email());
    user.setNome(request.email());
    user.setStatusUsuario(request.statusUsuario());
//    user.setTipoDeUsuario(request.getTipoDeUsuario());

    if (request.senha() != null && !request.senha().isEmpty()) {
      user.setSenha(passwordEncoder.encode(request.senha()));
    }

    userRepository.save(user);

  }
}
