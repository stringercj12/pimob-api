package org.example.pimob.application.services;

import org.example.pimob.communication.UserDto;
import org.example.pimob.domain.entities.Usuario;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  public Usuario Create(UserDto userDto){

    userRepository.findByEmail(userDto.getEmail()).ifPresent(u -> {
      throw new IllegalArgumentException("User with this email already exists.");
    });

    Usuario user = new Usuario();

    user.setAtivo(userDto.getAtivo());
    user.setEmail(userDto.getEmail());
    user.setNome(userDto.getNome());
    user.setSenha(userDto.getSenha());
    user.setStatusUsuario(userDto.getStatusUsuario());
    user.setTipoDeUsuario(userDto.getTipoDeUsuario());

   return userRepository.save(user);

  }
}
