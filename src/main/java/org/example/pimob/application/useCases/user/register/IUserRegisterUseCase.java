package org.example.pimob.application.useCases.user.register;

import org.example.pimob.communication.response.user.UserRequest;
import org.example.pimob.communication.response.user.UserResponse;

public interface IUserRegisterUseCase {
  UserResponse execute(UserRequest request);
}
