package org.example.pimob.application.useCases.user.getById;

import org.example.pimob.communication.response.user.UserResponse;

import java.util.UUID;

public interface IUserGetByIdUseCase {
  UserResponse execute(UUID id);
}
