package org.example.pimob.application.useCases.user.update;

import jakarta.transaction.Transactional;
import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUpdateUseCase implements IUserUpdateUseCase {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  public UserUpdateUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.bcryptPasswordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void execute(UserRegisterRequest request, UUID id) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    user.setEmail(request.getEmail());
    user.setNome(request.getEmail());
    user.setStatusUsuario(request.getStatusUsuario());
//    user.setTipoDeUsuario(request.getTipoDeUsuario());

    if (request.getSenha() != null && !request.getSenha().isEmpty()) {
      user.setSenha(bcryptPasswordEncoder.encode(request.getSenha()));
    }

    userRepository.save(user);

  }
}
