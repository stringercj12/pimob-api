package org.example.pimob.application.useCases.user.register;

import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.user.UserRegisterResponse;

public interface IUserRegisterUseCase {
  UserRegisterResponse execute(UserRegisterRequest request);
}
