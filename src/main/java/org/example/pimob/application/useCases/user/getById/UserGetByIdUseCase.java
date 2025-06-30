package org.example.pimob.application.useCases.user.getById;

import org.example.pimob.communication.response.user.UserResponse;
import org.example.pimob.exception.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGetByIdUseCase implements  IUserGetByIdUseCase{

  private final UserRepository userRepository;

  public UserGetByIdUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserResponse execute(Long id) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    return UserResponse.toDTO(user);
  }
}
