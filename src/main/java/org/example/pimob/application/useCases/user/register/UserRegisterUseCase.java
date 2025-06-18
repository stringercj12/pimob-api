package org.example.pimob.application.useCases.user.register;


import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.UserRegisterResponse;
import org.example.pimob.communication.response.UserResponse;
import org.example.pimob.domain.entities.User;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUseCase implements IUserRegisterUseCase{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserRegisterResponse execute(UserRegisterRequest request) {
    var userExists = userRepository.findByEmail(request.getEmail());

    if(userExists.isPresent()){
        throw new IllegalArgumentException("User with this email already exists.");
    }

    var user = new User();

    user.setAtivo(request.getAtivo());
    user.setEmail(request.getEmail());
    user.setNome(request.getNome());
    user.setSenha(request.getSenha());
    user.setStatusUsuario(request.getStatusUsuario());
    user.setTipoDeUsuario(request.getTipoDeUsuario());

    userRepository.save(user);

    return new UserRegisterResponse(user.getNome());
  }
}
