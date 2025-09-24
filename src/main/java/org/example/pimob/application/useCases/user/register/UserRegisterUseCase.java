package org.example.pimob.application.useCases.user.register;


import jakarta.transaction.Transactional;
import org.example.pimob.communication.response.user.UserRequest;
import org.example.pimob.communication.response.user.UserResponse;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.domain.mapping.UserMapping;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUseCase implements IUserRegisterUseCase {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEnconder;

  public UserRegisterUseCase(UserRepository userRepository, PasswordEncoder passwordEnconder) {
    this.userRepository = userRepository;
    this.passwordEnconder = passwordEnconder;
  }

  @Override
  @Transactional
  public UserResponse execute(UserRequest request) {
    var userExists = userRepository.findByEmail(request.email());

    if (userExists.isPresent()) {
      throw new IllegalArgumentException("User with this email already exists.");
    }

    UserEntity user = new UserEntity();

    user.setEmail(request.email());
    user.setNome(request.name());

    user.setSenha(passwordEnconder.encode(request.senha()));

//    user.setTipoDeUsuario(request.getTipoDeUsuario());
    user.setStatusUsuario(UserEntity.StatusUsuario.PENDENTE);
//    user.setAtivo(false);

    userRepository.save(user);

    return UserMapping.toDTO(user);
  }
}
