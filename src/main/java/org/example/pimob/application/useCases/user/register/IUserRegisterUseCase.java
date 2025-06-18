package org.example.pimob.application.useCases.user.register;

import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.UserRegisterResponse;
import org.example.pimob.communication.response.UserResponse;

public interface IUserRegisterUseCase {
  UserRegisterResponse execute(UserRegisterRequest request);
}
