package org.example.pimob.application.useCases.user.delete;

import jakarta.transaction.Transactional;
import org.example.pimob.exception.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteUseCase implements IUserDeleteUseCase {

  private final UserRepository  userRepository;

  public UserDeleteUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void execute(Long id) {
    var user =  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    userRepository.delete(user);

  }
}
