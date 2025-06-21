package org.example.pimob.application.useCases.user.getById;

import org.example.pimob.communication.response.UserResponse;

public interface IUserGetByIdUseCase {
  UserResponse execute(Long id);
}
