package org.example.pimob.application.useCases.user.getAll;

import org.example.pimob.communication.response.UserResponse;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGetAllUseCase implements IUserGetAllUseCase {

  private final UserRepository userRepository;

  public UserGetAllUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserResponse> execute() {
    var users = userRepository.findAll();

    return users.stream().map(UserResponse::toDTO).collect(Collectors.toList());
  }
}
