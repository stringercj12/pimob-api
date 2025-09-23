package org.example.pimob.application.useCases.user.getByEmail;

import org.example.pimob.communication.response.user.UserResponse;

public interface IUserGetByEmailUseCase {
  UserResponse execute(String email);
}
