package org.example.pimob.application.useCases.user.getAll;

import org.example.pimob.communication.response.user.UserResponse;

import java.util.List;

public interface IUserGetAllUseCase {
  List<UserResponse> execute();
}
