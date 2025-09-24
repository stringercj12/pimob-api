package org.example.pimob.application.useCases.user.update;


import org.example.pimob.communication.response.user.UserRequest;

import java.util.UUID;

public interface IUserUpdateUseCase {
  void execute(UserRequest request, UUID id);
}
