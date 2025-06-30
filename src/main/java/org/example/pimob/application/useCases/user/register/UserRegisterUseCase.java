package org.example.pimob.application.useCases.user.register;


import jakarta.transaction.Transactional;
import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.user.UserRegisterResponse;
import org.example.pimob.domain.entities.User;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUseCase implements IUserRegisterUseCase {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  public UserRegisterUseCase(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
  }

  @Override
  @Transactional
  public UserRegisterResponse execute(UserRegisterRequest request) {
    var userExists = userRepository.findByEmail(request.getEmail());

    if (userExists.isPresent()) {
      throw new IllegalArgumentException("User with this email already exists.");
    }

    var user = new User();

    user.setEmail(request.getEmail());
    user.setNome(request.getNome());

    user.setSenha(bcryptPasswordEncoder.encode(request.getSenha()));

    user.setTipoDeUsuario(request.getTipoDeUsuario());
    user.setStatusUsuario(User.StatusUsuario.PENDENTE);
    user.setAtivo(false);

    userRepository.save(user);

    return new UserRegisterResponse(user.getNome());
  }
}
