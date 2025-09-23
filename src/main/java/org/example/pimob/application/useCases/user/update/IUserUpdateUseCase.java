package org.example.pimob.application.useCases.user.update;

import org.example.pimob.communication.request.UserRegisterRequest;

import java.util.UUID;

public interface IUserUpdateUseCase {
  void execute(UserRegisterRequest request, UUID id);
}
