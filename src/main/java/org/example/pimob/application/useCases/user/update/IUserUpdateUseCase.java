package org.example.pimob.application.useCases.user.update;

import org.example.pimob.communication.request.UserRegisterRequest;

public interface IUserUpdateUseCase {
  void execute(UserRegisterRequest request, Long id);
}
