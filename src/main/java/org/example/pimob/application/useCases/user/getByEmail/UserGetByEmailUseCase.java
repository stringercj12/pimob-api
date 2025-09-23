package org.example.pimob.application.useCases.user.getByEmail;

import org.example.pimob.communication.response.user.UserResponse;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGetByEmailUseCase implements IUserGetByEmailUseCase {

  private final UserRepository userRepository;

  public UserGetByEmailUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserResponse execute(String email) {
    var user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    return UserResponse.toDTO(user);
  }
}
